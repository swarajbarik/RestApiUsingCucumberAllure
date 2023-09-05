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
                // Build and test using Maven
                bat "mvn clean test"
            }
        }

        stage('Generate Allure Report') {
            steps {
                // Generate Allure report
                bat "mvn allure:report"
            }
        }

        stage('Publish Allure Report') {
            steps {
                // Publish Allure report
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
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
