def call(String scanPath = ".", String outputFile = "trivy-report.txt") {
    stage('Trivy File System Scan') {
        echo "Running Trivy scan on path: ${scanPath}"
        sh """
            trivy fs ${scanPath} --format table > ${outputFile} || true
        """
        echo "Trivy scan completed. Report saved as ${outputFile}"
        archiveArtifacts artifacts: outputFile, onlyIfSuccessful: false
    }
}

// How to Use 
/*

@Library('Jenkins-Shared-Libraries') _

pipeline {
    agent any
    stages {
        stage('Security Scan') {
            steps {
                trivyScan('.', 'trivy-result.txt')  
            }
        }
    }
} 

*/
