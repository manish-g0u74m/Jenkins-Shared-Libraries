def call(int timeoutMinutes = 2) {
    stage('SonarQube Quality Gate') {
        echo "Waiting for SonarQube Quality Gate result..."

        try {
            timeout(time: timeoutMinutes, unit: 'MINUTES') {
                def qualityGate = waitForQualityGate(abortPipeline: true)
                echo "Quality Gate status: ${qualityGate.status}"

                if (qualityGate.status != 'OK') {
                    error "Pipeline aborted due to Quality Gate failure: ${qualityGate.status}"
                } else {
                    echo "Quality Gate passed successfully."
                }
            }
        } catch (err) {
            error "Quality Gate check failed or timed out: ${err}"
        }
    }
}

// How to use 
/*
        stage('Quality Gate') {
            steps {
                sonarQualityGate(3)   // waits up to 3 minutes (default is 2)
            }
        }
*/
