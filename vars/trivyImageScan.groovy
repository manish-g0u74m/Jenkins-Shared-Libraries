def call(String imageName, String outputFile = "trivy-image-report.txt") {
    stage("Trivy Image Scan") {
        echo "Starting Trivy scan for image: ${imageName}"

        sh """
            trivy image --severity HIGH,CRITICAL --format table ${imageName} > ${outputFile} || true
        """

        echo "Trivy image scan completed. Report saved to: ${outputFile}"

        archiveArtifacts artifacts: outputFile, onlyIfSuccessful: false
    }
}

/*  
-------------------------------------------------------
💡 Notes for Trivy Image Scan Function

This function performs a vulnerability scan on a Docker image
using Trivy and generates a readable report.

1. It scans the given Docker image (e.g., 'myapp:latest')  
   for HIGH and CRITICAL vulnerabilities.

2. The results are saved in a text file (default: trivy-image-report.txt).  
   You can provide a custom report filename if needed.

3. The report file is archived automatically in Jenkins so it can be
   downloaded or reviewed from the build page.

4. Example usage:
       trivyImageScan('wanderlust-frontend:latest')
       trivyImageScan('wanderlust-backend:v1', 'backend-scan.txt')

-------------------------------------------------------
*/
