import os
import jenkins
import yaml
import time


# Load in environment variables
JENKINS_URL = os.environ.get('JENKINS_URL')
TARGET_NAMESPACE = os.environ.get('TARGET_NAMESPACE')
PRODUCT = os.environ.get('PRODUCT')
TIME = os.environ.get('TIME')
S3_UPLOAD_URL = os.environ.get('S3_UPLOAD_URL')
PIPELINE_PATH = os.environ.get('PIPELINE_PATH')


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


def get_deployment_yaml_job(jenkins_api):
    print('Triggering job to check / update deployment.yaml')

    # Trigger a job to update deployments.yaml with LTF test status
    job_name = "core-e2e-deployments-updater"
    job_params = {
        "ENVIRONMENT": TARGET_NAMESPACE,
        "TIMESTAMP": TIME,
        "S3_PATH": S3_UPLOAD_URL,
        "PIPELINE_PATH": PIPELINE_PATH
    }

    jenkins_job_has_passed = run_job_and_wait_for_results(jenkins_api, job_name, job_params)

    if(jenkins_job_has_passed):
        print('Jenkins job "{}" completed successfully.'.format(job_name))
    else:
        print('Jenkins job "{}" did not complete successfully.'.format(job_name))


def init():
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
        return None

    try:
        # Instatiate connection to Jenkins instance
        jenkins_api = jenkins.Jenkins(JENKINS_URL, username=jenkins_username, password=jenkins_password)
    except Exception as e:
        print('Failed to connect to Jenkins instance {}: {}'.format(JENKINS_URL, e))
        return None

    return jenkins_api


def main():
    if(PRODUCT.lower() == "e2e"):
        jenkins_api = init()

        # Check it we received a valid api object
        if jenkins_api is not None:
            get_deployment_yaml_job(jenkins_api)
    else:
        print('Not running any pre-test hooks as this is not an e2e test.')


if __name__ == '__main__':
    main()
