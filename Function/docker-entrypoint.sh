#!/bin/bash
export SLACK_URL='https://hooks.slack.com/services/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' # Your slack URL
export TIMESTAMP=${TIMESTAMP:-`date +%Y-%m-%d_%H-%M-%S`}
export CONSOLE_LOG="/tmp/docker-entrypoint_console_${TIMESTAMP}.log"
export PAYLOAD_FILE='/tmp/payload.json'
export S3_UPLOAD_PATH="${PRODUCT}/${CHART_NAME}/${TARGET_NAMESPACE}/${TIMESTAMP}"
export S3_UPLOAD_URL="s3://ho.ipt.ops.testresults/${S3_UPLOAD_PATH}"
export S3_TEMP_PATH="TEMP_REPORTS/${PRODUCT}/${CHART_NAME}/${TARGET_NAMESPACE}/${TIMESTAMP}"
export S3_TEMP_UPLOAD_URL="s3://ho.ipt.ops.testresults/${S3_TEMP_PATH}"
export S3_BUCKET='https://s3.console.aws.amazon.com/s3/buckets'
export TEST_VIEWER='https://eue-api-test-dashboard-ttzo-kube1.service.ops.iptho.co.uk/s3'
export TEST_VIEWER_PATH="${TEST_VIEWER}/${S3_UPLOAD_PATH}/serenity/index.html"
export SUCCESS_MESSAGE="${CHART_NAME} SUCCEED :heavy_check_mark: TARGET_NAMESPACE=${TARGET_NAMESPACE} \nView test report on <${TEST_VIEWER_PATH}|Serenity-Report>"
export COMPLETE_MESSAGE="${CHART_NAME} COMPLETED :heavy_check_mark: TARGET_NAMESPACE=${TARGET_NAMESPACE} \nView test report on <${TEST_VIEWER_PATH}|Serenity-Report>"
export FAILURE_MESSAGE="${CHART_NAME} FAILED :x: TARGET_NAMESPACE=${TARGET_NAMESPACE} \nView test report on <${TEST_VIEWER_PATH}|Serenity-Report>"
export REPORT_DIR="/app/Function/target/site/reports"
export JENKINS_JOB_NAME="${JENKINS_JOB_NAME:-'core-sandbox'}"
export JENKINS_ROLLBACK_JOB_NAME="${JENKINS_ROLLBACK_JOB_NAME:-'core-sandbox'}"
export JENKINS_UMBRELLA_VERSION="${JENKINS_UMBRELLA_VERSION:-'0.0.0-UNUSED'}"
export MAVEN_OPTS="-Xms512M -Xmx1024M -XX:MaxMetaspaceSize=512M -DproxySet=true -Dhttps.proxyHost=${OUTBOUND_PROXY_HOST} -Dhttps.proxyPort=${OUTBOUND_PROXY_PORT} -Dhttp.nonProxyHosts=*.iptho.co.uk -Djavax.net.ssl.trustStore=/home/javauser/.m2/cacerts -Djavax.net.ssl.trustStoreType=JKS -Djavax.net.ssl.trustStorePassword=changeit"
export BATCHES_COMPLETED=0

GenerateRunners()
{
    echo "Generating the runners..."
    STATUS=1
    RETRIES=0
    while [[ ${STATUS} -ne 0 && ${RETRIES} -lt 5 ]]
    do
        echo "RETRY = ${RETRIES}"
        if [ -z ${TEST_TAGS} ]; then
            echo "No test tags supplied."
            mvn clean -Pcucumber_parallel_plugin generate-test-sources
        else
            echo "Test tags present, running with: ${TEST_TAGS}"
            mvn clean -Pcucumber_parallel_plugin generate-test-sources -Dcucumber.options="--tags ${TEST_TAGS}"
        fi
        STATUS=$?
        RETRIES=$(( RETRIES + 1 ))
    done

    echo "Copying the runner \"target/generated-test-sources/cucumber/${BATCH_NAME}.java\" into \"src/test/java\""
    cp -v target/generated-test-sources/cucumber/${BATCH_NAME}.java src/test/java/
}

UpdateBatchCM()
{
    BATCH_STATUS=$1
    DTTM=$(date +%Y-%m-%d_%H:%M:%S)
    BATCH_STATUS="${BATCH_STATUS} on ${DTTM}"

    # Get a copy of the Configmap.
	kubectl get configmap ${BATCH_CONFIGMAP_NAME} -o yaml > /tmp/configmap.yaml

	# Get the count of completed batches.
	BATCHES_COMPLETED=$(egrep -o "BatchesCompleted: \"[0-9]+\"" /tmp/configmap.yaml | sed -e 's/\"//g' | awk -F ': ' '{print $2}')

    # Updating CM with incremented value of BatchesCompleted
	BATCHES_COMPLETED=$((BATCHES_COMPLETED + 1))
	echo "Updating Configmap \"${BATCH_CONFIGMAP_NAME}\" with BatchesCompleted: ${BATCHES_COMPLETED}"
	sed -E -i "s/BatchesCompleted: \"[0-9]+\"/BatchesCompleted: \"${BATCHES_COMPLETED}\"/g" /tmp/configmap.yaml

    # Updating CM with current batch run status
    echo "Updating Configmap \"${BATCH_CONFIGMAP_NAME}\" with ${RUNNER}batch${BATCH_NUMBER}: ${BATCH_STATUS}"
    sed -E -i "s/${BATCH_NAME}: .*/${BATCH_NAME}: ${BATCH_STATUS}/" /tmp/configmap.yaml

    # Updaing last updated by:
    echo "Updating last updated by ${HOSTNAME}"
    sed -E -i "s/LastUpdatedBy: .*/LastUpdatedBy: ${HOSTNAME}/" /tmp/configmap.yaml

    # Apply CM Updates
    echo "Applying the updated Configmap to \"${BATCH_CONFIGMAP_NAME}\""
    kubectl -n ttzo apply -f /tmp/configmap.yaml && return 0 || return 1
}

AggregateAndUploadReports()
{
    echo "Preparing to generate aggregate report."
    echo "Downloading reports from ${S3_TEMP_UPLOAD_URL} into ${REPORT_DIR}"
    aws s3 cp ${S3_TEMP_UPLOAD_URL}  ${REPORT_DIR} --recursive > /dev/null

    # Aggregate report
    mvn serenity:aggregate

    # Upload to S3
    echo "Uploading aggregate report to ${S3_UPLOAD_URL}"
    aws s3 cp ${REPORT_DIR} ${S3_UPLOAD_URL} --recursive > /dev/null
}

SlackTestStatus()
{
    echo "Posting the aggregate report on Slack"
    # Count number of test failures.
    FAILURE_COUNTS=$(awk '/FAILURE/ {count++} END{print count}' target/site/reports/serenity/results.csv)
    echo ${FAILURE_COUNTS}

    if [ ${FAILURE_COUNTS} -gt 0 ]; then
        echo ${FAILURE_MESSAGE}
        cat <<EOF > ${PAYLOAD_FILE}
    {
        "text" : "<!here> ${FAILURE_MESSAGE}",
        "unfurl_links": true
    }
EOF
    else
        echo ${SUCCESS_MESSAGE}
        cat <<EOF > ${PAYLOAD_FILE}
    {
        "text" : "<!here> ${SUCCESS_MESSAGE}",
        "unfurl_links": true
    }
EOF
    fi

    if [ -n "${SLACK_URL}" ]; then
        cat ${PAYLOAD_FILE}
        curl -X POST -H 'Content-type: application/json' --data @${PAYLOAD_FILE} ${SLACK_URL}
    fi
}

### Main starts here ###
{
    python preTestHooks.py

    # switching to outbound proxy for application connections.
    export https_proxy="${OUTBOUND_PROXY_HOST}:${OUTBOUND_PROXY_PORT}"
    export http_proxy="${OUTBOUND_PROXY_HOST}:${OUTBOUND_PROXY_PORT}"

    # Generate Runners.
    GenerateRunners

    # Execute maven tests.
    echo mvn clean verify -offline -Dit.test=${BATCH_NAME} -Dcontext=${BATCH_NAME}
    mvn clean verify -offline -Dit.test=${BATCH_NAME} -Dcontext=${BATCH_NAME}

    MVN_STATUS=$?
    BATCH_STATUS=$([ "$MVN_STATUS" -gt 0 ] && echo "Failed" || echo "Completed" )

	# switching back to lcoal proxy for slack and aws connections.
	export https_proxy="${LOCAL_PROXY_HOST}:${LOCAL_PROXY_PORT}"
	export http_proxy="${LOCAL_PROXY_HOST}:${LOCAL_PROXY_PORT}"

	echo "Uploading reports to ${S3_UPLOAD_URL}/temp"
	aws s3 cp ${REPORT_DIR} ${S3_TEMP_UPLOAD_URL} --recursive > /dev/null

    # Update the Configmap with retry attempts upon failure.
    COUNTER=0
    while [ ${COUNTER} -lt ${TOTAL_BATCHES} ]
    do
        UpdateBatchCM ${BATCH_STATUS}
        STATUS=$?
        if [ ${STATUS} -eq 0 ]; then
            break
        fi
        COUNTER=$((COUNTER + 1))
        sleep 3
    done

	if [ ${BATCHES_COMPLETED} -eq ${TOTAL_BATCHES} ]; then
        # Aggregate Report
        AggregateAndUploadReports
        # Slack Message Test Status
        SlackTestStatus
        python testAnalyser.py
	fi

	echo "Finished"
} 2>&1 | tee ${CONSOLE_LOG}

sleep 1200
