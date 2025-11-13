def call(String projectKey, String projectName, String scannerTool = '', String sonarServer = '') {
    stage('SonarQube Scan') {
        echo "Starting SonarQube scan for: ${projectName}"

        // Get server and scanner dynamically or use defaults if not provided
        def server = sonarServer ?: 'sonar'
        def scanner = scannerTool ?: 'sonar'

        withSonarQubeEnv(server) {
            def scannerHome = tool scanner
            sh """
                ${scannerHome}/bin/sonar-scanner \
                -Dsonar.projectKey=${projectKey} \
                -Dsonar.projectName=${projectName} \
                -Dsonar.sources=. \
                -Dsonar.sourceEncoding=UTF-8
            """
        }

        echo "SonarQube scan completed successfully for ${projectName}"
    }
}

// How to Use 
// How to Use:
/*
   1. Default usage (uses default server & scanner names: 'sonar')
      stage('SonarQube Scan') {
          steps {
              sonarScan('sample-app', 'Sample Application')
          }
      }

   2️. Custom usage (if you change SonarQube Server or Tool names in Jenkins)
      stage('SonarQube Scan') {
          steps {
              sonarScan('sample-app', 'Sample Application', 'sonarQScanner', 'mySonarServer')
          }
      }
*/
