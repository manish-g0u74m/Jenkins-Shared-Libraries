def call(String projectName = 'CICD App', String recipientM = 'manish.sharma.devops@gmail.com') {
    post {
        success {
            emailext(
                to: recipient,
                subject: "✅ Build Successful for ${projectName}",
                body: """
                    Hello Team,

                    The Jenkins build for *${projectName}* has completed successfully.

                    Build URL: ${env.BUILD_URL}
                    Job: ${env.JOB_NAME}
                    Build Number: ${env.BUILD_NUMBER}

                    Regards,
                    Jenkins Automation
                """
            )
        }

        failure {
            emailext(
                to: recipient,
                subject: "❌ Build Failed for ${projectName}",
                body: """
                    Hello Team,

                    The Jenkins build for *${projectName}* has failed.

                    Build URL: ${env.BUILD_URL}
                    Job: ${env.JOB_NAME}
                    Build Number: ${env.BUILD_NUMBER}

                    Please check the Jenkins logs for details.

                    Regards,
                    Jenkins Automation
                """
            )
        }
    }
}

/*  
-------------------------------------------------------
💡 Notes for notifyBuild Function

1. This function sends email notifications for both successful
   and failed Jenkins builds using the built-in 'emailext' plugin.

2. It uses environment variables like BUILD_URL and JOB_NAME to
   include useful info automatically in the email body.

3. You can call it at the end of your pipeline to keep Jenkinsfile clean.

4. Example usage:
       notifyBuild('Wanderlust CICD App', 'manish.sharma.devops@gmail.com')

-------------------------------------------------------
# Use in Jenkinsfile 
@Library('Jenkins-Shared-Libraries') _

pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo "Building Wanderlust..."
            }
        }

        stage('Test') {
            steps {
                echo "Running tests..."
            }
        }
    }

    // Instead of writing success/failure again, just call this:
    post {
        always {
            notifyBuild('Wanderlust CICD App', 'manish.sharma.devops@gmail.com')
        }
    }
}
*/
