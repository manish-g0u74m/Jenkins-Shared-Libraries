def call(String project, String imageTag = 'latest', String dockerHubUser = '') {
    stage("Remove Docker Image") {
        def imageFullName = dockerHubUser ? "${dockerHubUser}/${project}:${imageTag}" : "${project}:${imageTag}"
        echo "Attempting to remove image: ${imageFullName}"

        sh """
            if docker image inspect ${imageFullName} > /dev/null 2>&1; then
                docker rmi -f ${imageFullName}
                echo "Image ${imageFullName} removed successfully."
            else
                echo "Image ${imageFullName} not found. Skipping removal."
            fi
        """
    }
}

/*  
-------------------------------------------------------
💡 Notes for Docker Remove Function

This function deletes a Docker image locally using its full name
(e.g., user/project:tag).

1. It safely checks whether the image exists before trying to remove it.
   That prevents pipeline failures if the image doesn’t exist.

2. By default, it assumes the tag is 'latest'.
   You can pass a custom tag when needed.

3. Example usage:
       dockerRemove('wanderlust-frontend', 'latest', 'g0u74m')
       dockerRemove('wanderlust-backend', 'v1.0', 'g0u74m')
       dockerRemove('sample-app')   // Removes sample-app:latest

4. Works both for local images and tagged images under a Docker Hub user.

-------------------------------------------------------
*/
