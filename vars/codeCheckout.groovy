def call(String GitUrl, String GitBranch = 'main') {
    stage('Checkout Code') {
        echo "Cloning repository: ${GitUrl} (branch: ${GitBranch})"
        try {
            git url: GitUrl, branch: GitBranch
            echo "Code checkout completed successfully."
        } catch (err) {
            error "Checkout failed: ${err}"
        }
    }
}

// How to Use 
/*
stage('Checkout') {
   steps {
       codeCheckout('https://github.com/manish-g0u74m/sample-app.git', 'main') 
   }
}

By Default it will take main Branch We can change according to Our Branch Name 
*/
