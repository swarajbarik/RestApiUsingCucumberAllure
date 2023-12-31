pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout your source code from the GitHub repository
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/swarajbarik/RestApiUsingCucumberAllure.git']]])
            }
        }

        stage('Build and Test') {
            steps {
                 catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                 bat "mvn clean install"
                 bat "mvn test"
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                    bat "mvn allure:report"
            }
        }

    
        stage('Publish HTML Report') {
            steps {
                // Archive the HTML report
                archiveArtifacts artifacts: 'target\\site\\allure-maven-plugin\\*'
                
                // Publish the HTML report using the HTML Publisher Plugin
                publishHTML(target: [
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target\\site\\allure-maven-plugin',
                    reportFiles: 'index.html',
                ])
            }
        }
    }

    post {
        always {
            // Archive the generated Allure report artifacts
            archiveArtifacts artifacts: 'target/allure-results/*'
        }
    }
}
