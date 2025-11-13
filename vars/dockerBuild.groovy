def call(String imageName, String imageTag = 'latest', String dockerfilePath = 'Dockerfile', String contextPath = '.') {
    stage('Docker Build') {
        echo "Building Docker image: ${imageName}:${imageTag}"
        sh "docker build -t ${imageName}:${imageTag} -f ${dockerfilePath} ${contextPath}"
        echo "Docker image built successfully."
    }
}


// # How to Use 
/* Example 
dockerBuild('wanderlust-frontend', 'latest', './frontend/Dockerfile', './frontend')
dockerBuild('wanderlust-backend', 'latest', './backend/Dockerfile', './backend')
dockerBuild('sample-app', 'v1', './app/Dockerfile', './app')
*/  
/* Another Example 
        stage('Docker Image Build') {
            steps {
                echo "Starting Docker builds for frontend and backend..."

                // Frontend image build
                dockerBuild(
                    'wanderlust-frontend', 
                    'latest', 
                    './frontend/Dockerfile', 
                    './frontend'
                )

                // Backend image build
                dockerBuild(
                    'wanderlust-backend', 
                    'latest', 
                    './backend/Dockerfile', 
                    './backend'
                )
            }
        }
*/
