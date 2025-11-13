def call(String composeFile = 'docker-compose.yml', String action = 'up -d') {
    stage("Docker Compose: ${action}") {
        echo "Running Docker Compose command: docker compose -f ${composeFile} ${action}"

        sh """
            docker compose -f ${composeFile} ${action}
        """

        echo "Docker Compose action '${action}' executed successfully."
    }
}

/*  
-------------------------------------------------------
💡 Notes for Docker Compose Function

This function runs Docker Compose commands dynamically
based on the parameters provided.

1. By default, it executes:
       docker compose -f docker-compose.yml up -d

2. You can use it to run any Compose operation, such as:
       dockerCompose()                           // Default: up -d
       dockerCompose('docker-compose.dev.yml')   // Custom file
       dockerCompose('docker-compose.yml', 'down')  // Stop containers
       dockerCompose('docker-compose.yml', 'build') // Build services

3. The function automatically logs progress and makes it
   easy to manage multiple Compose environments inside Jenkins.

4. Example usage in pipeline:
       stage('Docker Compose Up') {
           steps {
               dockerCompose('docker-compose.yml', 'up -d')
           }
       }

       stage('Docker Compose Down') {
           steps {
               dockerCompose('docker-compose.yml', 'down')
           }
       }

-------------------------------------------------------
*/
