def call(String scanPath = './', String odcTool = 'OWASP', String nvdApiKey = '') {
    stage('OWASP Dependency Check') {
        echo "Starting OWASP Dependency Check on path: ${scanPath}"

        // Build the arguments dynamically
        def args = "--scan ${scanPath}"
        if (nvdApiKey?.trim()) {
            args += " --nvdApiKey ${nvdApiKey}"
        }

        dependencyCheck additionalArguments: args, odcInstallation: odcTool
        dependencyCheckPublisher pattern: '**/dependency-check-report.xml'

        echo "OWASP Dependency Check completed successfully."
    }
}
// How To use 
/* 
    1. Default (simple):
          owaspScan()
          
    2. With custom tool name and API key:
          owaspScan('./', 'dc', 'b588dcc5-7433-4160-b160-8d774e5b866')

    3. With a different scan path:
          owaspScan('src/', 'OWASP')

Notes:
odcTool → name of your OWASP Dependency Check installation in Jenkins
nvdApiKey → optional (only needed if you’ve configured NVD API rate limits or want faster scans)
The generated report will appear as dependency-check-report.xml
*/
