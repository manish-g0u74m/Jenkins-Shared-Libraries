# Jenkins Shared Library

This repository contains reusable **Jenkins Shared Libraries** created for automating CI/CD workflows.  
Shared libraries in Jenkins allow you to encapsulate common pipeline logic (build, scan, push, notifications, etc.) into reusable functions — keeping pipelines clean, modular, and easy to maintain.

---

## 📁 Repository Structure

A Jenkins shared library must include a **`vars/`** directory.  
Each file inside `vars/` represents a global function that Jenkins can automatically recognize and use inside a pipeline.

Example structure of this repository:
    Jenkins-Shared-Libraries/
              └── vars/
              |      ├── dockerBuild.groovy
              |      ├── dockerPush.groovy
              |      ├── dockerRemove.groovy
              |      ├── trivyImageScan.groovy
              |      ├── owaspScan.groovy
              |      ├── sonarScan.groovy
              |      ├── sonarQualityGate.groovy
              |      ├── dockerCompose.groovy
              |      ├── notifyBuild.groovy
              |      └── codeCheckout.groovy
              └── README.md

Each `.groovy` file defines a `call()` method — the main entry point Jenkins uses when the function is called.

---

## 🛠️ How to Configure the Shared Library in Jenkins

1. **Login to your Jenkins dashboard**

2. Go to  
   `Manage Jenkins → Configure System → Global Pipeline Libraries`

3. Add a new library configuration:  
   | Field  | Value |
   |--------|-------|
   | **Name** | `Shared` |
   | **Default Version** | `main` |
   | **Project Repository** | `https://github.com/manish-g0u74m/Jenkins-Shared-Libraries.git` |

4. Click **Save**

---

## 🧩 How to Use the Shared Library in a Pipeline

1. In your **Declarative Jenkinsfile**, import the library at the very top:
   ```groovy
   @Library('Shared') _

2. We can now directly call any shared function.
   For example:
   ```bash
   pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                codeCheckout('https://github.com/manish-g0u74m/sample-app.git', 'main')
            }
        }

        stage('Docker Build') {
            steps {
                dockerBuild('wanderlust-frontend', 'latest', './frontend/Dockerfile', './frontend')
            }
        }

        stage('Trivy Image Scan') {
            steps {
                trivyImageScan('wanderlust-frontend:latest')
            }
        }

        stage('SonarQube Scan') {
            steps {
                sonarScan('sample-app', 'Sample Application')
            }
        }

        stage('Quality Gate') {
            steps {
                sonarQualityGate(3)
            }
        }

        stage('OWASP Scan') {
            steps {
                owaspScan('./', 'dc')
            }
        }

        stage('Docker Push') {
            steps {
                dockerPush('wanderlust-frontend', 'latest')
            }
        }

        stage('Notify') {
            steps {
                notifyBuild('Wanderlust CICD App', 'manish.sharma.devops@gmail.com')
                }
            }
        }
    }
```
