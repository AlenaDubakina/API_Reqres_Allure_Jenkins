pipeline {
    agent any

    environment {
        BASE_URI = 'https://reqres.in'
        API_KEY = credentials('api-key')
    }

    triggers {
        cron('H 9 * * 1-5')
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

                stage('Generate config.properties') {
                    steps {
                        sh '''
                            # Создаем или перезаписываем файл config.properties
                            echo "base.uri=${BASE_URI}" > src/main/resources/config.properties
                            echo "api.key=${API_KEY}" >> src/main/resources/config.properties
                        '''
                        echo 'Файл src/main/resources/config.properties успешно создан.'
                    }
                }

        stage('Build & Test') {
            steps {
                echo "Running tests"
                sh 'mvn clean test'
            }
        }
    }

    post {
       always {
                echo "Allure report generation"
                allure includeProperties: false,
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'target/allure-results']]
                echo 'Pipeline finished.'
       }
       success {
            echo 'Pipeline succeeded!'
            notifyTelegram("SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}")
       }
       failure {
            echo 'Pipeline failed.'
            notifyTelegram("FAIlED: ${env.JOB_NAME} #${env.BUILD_NUMBER}")
       }
    }
}

    def notifyTelegram(message){
    withCredentials([string(credentialsId: 'telegram-token', variable: 'TELEGRAM_TOKEN'),
    string(credentialsId: 'telegram-chat-id', variable: 'TELEGRAM_CHAT_ID')])
    {
     sh """ curl -s -X POST https://api.telegram.org/bot${TELEGRAM_TOKEN}/sendMessage \
                    -d chat_id=${TELEGRAM_CHAT_ID} \
                    -d text="${message}"
        """
    }
    }