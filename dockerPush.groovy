def call(String imageName, String imageTag = 'latest', String registryUserVar = 'dockerHubUser', String credentialsId = 'dockerHubCreds') {
    stage("Docker Push: ${imageName}") {
        echo "Preparing to push image: ${imageName}:${imageTag}"

        withCredentials([usernamePassword(
            credentialsId: credentialsId,
            usernameVariable: registryUserVar,
            passwordVariable: 'dockerHubPass'
        )]) {
            sh """
                echo "\$dockerHubPass" | docker login -u \$$registryUserVar --password-stdin
                docker tag ${imageName}:${imageTag} \$$registryUserVar/${imageName}:${imageTag}
                docker push \$$registryUserVar/${imageName}:${imageTag}
                docker logout
            """
        }

        echo "Docker image ${imageName}:${imageTag} pushed successfully."
    }
}

// How to use 
/* 
-------------------------------------------------------
💡 Notes for Docker Push Function

This function is designed to automate the image push process
in a simple and reusable way. Here’s what it does:

1. Logs in to Docker Hub (or any registry) using the Jenkins
   credentials provided through 'credentialsId' (default: dockerHubCreds).

2. Tags the local Docker image with the username from your
   Jenkins credentials so it can be uploaded to the registry.

3. Pushes the tagged image to the specified registry.

4. Logs out automatically once the push is complete to keep
   the environment clean and secure.

You can reuse this function for any image name, not just frontend
or backend. Examples:

    dockerPush('myapp', 'v1.0')
    dockerPush('api-service', 'stable')

If you’re using a different container registry (for example,
AWS ECR or Google Container Registry), you can override the
default credential parameters like this:

    dockerPush('myapp', 'v1.0', 'ecrUser', 'aws-ecr-cred')

-------------------------------------------------------
*/
