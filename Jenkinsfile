pipeline {
    agent any

    triggers {
        cron(H 9 * * 1-5)
    }

    tools {
        maven 'Maven_3'
        jdk 'JDK_25'
    }
    stages {
        stage('Checkout') {
            steps {
                echo "Geting the project from GitHub"
                git url: 'https://github.com/AlenaDubakina/API_Reqres_Allure_Jenkins', branch: 'main'
            }
        }

        stage('Build & Test') {
            steps {
                echo "Running tests"
                sh 'mvn clean test'
            }
        }

        stage('Allure Report') {
            steps {
                echo "Allure report generation"
                allure includeProperties: false,
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
       always {
            echo "Pipeline finished."
       }
       success {
            echo 'Pipeline succeeded!'
       }
       failure {
            echo "Pipeline failed."
       }
    }
}