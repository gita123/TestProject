
import os
from xml.dom import minidom

import jenkins
import yaml
import time

# Load environment variables
jenkins_url = os.environ.get('JENKINS_URL')
jenkins_job_name = os.environ.get('JENKINS_JOB_NAME')
jenkins_rollback_job_name = os.environ.get('JENKINS_ROLLBACK_JOB_NAME')
jenkins_umbrella_version = os.environ.get('JENKINS_UMBRELLA_VERSION')
umbrella_project = os.environ.get('HELM_CHART_TEAM')

# Dashboard
TIME = os.environ.get('TIME')
S3_UPLOAD_URL = os.environ.get('S3_UPLOAD_URL')
PIPELINE_PATH = os.environ.get('PIPELINE_PATH')

# If "true" we should trigger e2e job or deploy to next highest environment
pipeline_auto_promote = True if os.environ.get('SHOULD_PROMOTE') == 'true' else False

# Should be E2E, LTF etc
test_domain = os.environ.get('DOMAIN')
product_name = os.environ.get('PRODUCT')

# Load in environment variables for e2e test job
e2e_job_params = {}

e2e_job_params['CLUSTER'] = "ops-kops1"
e2e_job_params['NAMESPACE'] = "ttzo"
e2e_job_params['TARGET'] = os.environ.get('TARGET_NAMESPACE')
e2e_job_params['VERSION'] = os.environ.get('JENKINS_E2E_CHART_VERSION')
e2e_job_params['CHART'] = os.environ.get('JENKINS_E2E_CHART_NAME')
e2e_job_params['TEST_TAGS'] = os.environ.get('JENKINS_E2E_TEST_TAGS')

# Load in environment variables for deployment job
deploy_job_params = {}
deploy_job_params['VERSION'] = jenkins_umbrella_version
deploy_job_params['TARGET'] = os.environ.get('TARGET_NAMESPACE')
deploy_job_params['FORCE_DEPLOY'] = True


def run_job_and_wait_for_results(jenkins_connection, job_name, job_params={'empty': 'yes'}):
    try:
        # Run the deployment job for higher environment
        queue_number = jenkins_connection.build_job(job_name, parameters=job_params)

        # Spin until the job is loaded and running on an executor
        while(jenkins_connection.get_queue_item(queue_number)['why'] is not None):
            time.sleep(5)

        # Retrieve the build number for the job that is running
        job_number = jenkins_connection.get_queue_item(queue_number)['executable']['number']

        # Spin until the job is no longer running
        while(jenkins_connection.get_build_info(job_name, job_number)['building']):
            time.sleep(10)   # Delay for 10 seconds.

        job_passed = False

        # Check the final result of the job (pass/fail)
        if(jenkins_connection.get_build_info(job_name, job_number)['result'].upper() == "SUCCESS"):
            job_passed = True

        return job_passed
    except Exception as e:
        print 'Failed to execute Jenkins Job.\nException error is: {}'.format(e)
        return False


def update_deployment_yaml(jenkins_api, fail_count, error_count):
    print('Triggering job to update deployment.yaml')

    # Trigger a job to update deployments.yaml with LTF test status
    job_name = "core-deployments-updater"
    job_params = {
        "PROJECT": umbrella_project,
        "ENVIRONMENT": os.environ.get('TARGET_NAMESPACE'),
        "VERSION": jenkins_umbrella_version,
        "TIMESTAMP": TIME,
        "S3_PATH": S3_UPLOAD_URL,
        "PIPELINE_PATH": PIPELINE_PATH,
        "PASSED": True if fail_count == 0 and error_count == 0 else False
    }

    jenkins_job_has_passed = run_job_and_wait_for_results(jenkins_api, job_name, job_params)

    if(jenkins_job_has_passed):
        print('Jenkins job "{}" completed successfully.'.format(job_name))
    else:
        print('Jenkins job "{}" did not complete successfully.'.format(job_name))


def trigger_dashboard_job(jenkins_api):
    print('Triggering Rebuild of Dashboard')

    job_params = {
        "ENVIRONMENT": os.environ.get('TARGET_NAMESPACE'),
        "PROJECT": test_domain,
        "CONFLUENCE_ANCHOR_ID": os.environ.get('CONFLUENCE_ANCHOR_ID'),
        "CONFLUENCE_USER": os.environ.get('CONFLUENCE_USER'),
        "CONFLUENCE_SPACE_KEY": os.environ.get('CONFLUENCE_SPACE_KEY'),
        "CONFLUENCE_VAULT_PATH": os.environ.get('CONFLUENCE_VAULT_PATH'),
        "PIPELINE_PATH": os.environ.get('PIPELINE_PATH')
    }


    job_name = "core-dash"

    jenkins_job_has_passed = run_job_and_wait_for_results(jenkins_api, job_name, job_params)

    if(jenkins_job_has_passed):
        print('Jenkins job completed successfully.')
    else:
        print('Jenkins job did not complete successfully.')


def process_results():
    print("Starting Test Analysis Script")

    try:
        xmldoc = minidom.parse('/app/Function/target/failsafe-reports/failsafe-summary.xml')
        fail_count = int(xmldoc.getElementsByTagName('failures')[0].childNodes[0].nodeValue)
        error_count = int(xmldoc.getElementsByTagName('errors')[0].childNodes[0].nodeValue)
    except IOError:
        fail_count = -1
        error_count = -1

    # Jenkins credentials
    jenkins_username = 'eue_jenkins_user'

    # Try to load Jenkins credentials
    try:
        with open('/app/Function/src/test/resources/credentials/jenkinsauth.yaml') as f:
            # use safe_load instead load
            secretsMap = yaml.safe_load(f)
            jenkins_password = secretsMap['value']
    except Exception:
        print('Failed to load Jenkins credentials file, exiting.')
        return

    # Check if any of the environment vars are not present
    invalid_env_vars = [None, "default"]
    if any(True for x in invalid_env_vars if x in [jenkins_url, jenkins_job_name, jenkins_rollback_job_name]):
        print("Environment variables not present")
        return

    try:
        # Instatiate connection to Jenkins instance
        jenkins_api = jenkins.Jenkins(jenkins_url, username=jenkins_username, password=jenkins_password)
    except Exception as e:
        print('Failed to connect to Jenkins instance {}: {}'.format(jenkins_url, e))
        return

    # We run the below only if we're to auto promote
    if (pipeline_auto_promote):
        # Check what the DOMAIN env var is set to. If it is e2e then we should handle logic for e2e results. Otherwise assume it is LTF
        if(product_name.lower() == "e2e"):
            process_e2e_results(jenkins_api, fail_count, error_count)
        else:
            process_ltf_results(jenkins_api, fail_count, error_count)
    # Always trigger rebuild of dashboard regardless of auto_promote status
    trigger_dashboard_job(jenkins_api)


def process_ltf_results(jenkins_api, fail_count, error_count):

    tests_passed = True if fail_count == 0 and error_count == 0 else False   

    # Call Jenkins job to update deployment yaml information with LTF results
    update_deployment_yaml(jenkins_api, fail_count, error_count)

    if tests_passed:
        print("No Failures Detected, triggering jenkins deploy")

        # When we are to run an e2e test
        jenkins_job_params = {
            "CLUSTER": e2e_job_params['CLUSTER'],
            "NAMESPACE": e2e_job_params['NAMESPACE'],
            "TARGET": e2e_job_params['TARGET'],
            "VERSION": e2e_job_params['VERSION'],
            "CHART": e2e_job_params['CHART'],
            "TEST_TAGS": e2e_job_params['TEST_TAGS']
        }

        jenkins_job_has_passed = run_job_and_wait_for_results(jenkins_api, jenkins_job_name, jenkins_job_params)

        print('We tested against chart version {}.'.format(jenkins_umbrella_version))

        if(jenkins_job_has_passed):
            print('Jenkins job completed successfully.')
        else:
            print('Jenkins job did not complete successfully.')
    else:
        print("{} failures detected and {} errors detected, therefore not deploying to higher environment".format(
            fail_count, error_count))

        # We pass in the environment which has failed tests, so we can trigger a job to roll back deployment
        jenkins_job_params = {
            "NAMESPACE": e2e_job_params['TARGET'],
        }

        # Rollback to last version that passed tests
        run_job_and_wait_for_results(jenkins_api, jenkins_rollback_job_name, jenkins_job_params)


def process_e2e_results(jenkins_api, fail_count, error_count):
    if fail_count == 0 and error_count == 0:
        print("No Failures Detected, triggering jenkins deploy")

        # When we're deploying a chart to a higher environment
        jenkins_job_params = {
            "VERSION": deploy_job_params['VERSION'],
            "FORCE_BUILD": deploy_job_params['FORCE_DEPLOY']
        }

        jenkins_job_has_passed = run_job_and_wait_for_results(jenkins_api, jenkins_job_name, jenkins_job_params)

        print('We tested against chart version {}.'.format(jenkins_umbrella_version))

        if(jenkins_job_has_passed):
            print('Jenkins job completed successfully.')
        else:
            print('Jenkins job did not complete successfully.')
    else:
        print("{} failures detected and {} errors detected, therefore not deploying to higher environment".format(
            fail_count, error_count))

        # We pass in the environment which has failed tests, so we can trigger a job to roll back deployment
        jenkins_job_params = {
            "NAMESPACE": deploy_job_params['TARGET'],
        }

        # Rollback to last version that passed tests
        run_job_and_wait_for_results(jenkins_api, jenkins_rollback_job_name, jenkins_job_params)


def main():
    process_results()


if __name__ == '__main__':
    main()
