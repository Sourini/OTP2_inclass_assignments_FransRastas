pipeline {
    agent any

    tools {
        maven 'maven3'
    }

    environment {
        PATH = "C:\\Users\\scuba\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin;${env.PATH}"
        DOCKERHUB_REPO = 'sourini/temperature-converter-localization'
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build(
                        "${DOCKERHUB_REPO}:${BUILD_NUMBER}"
                    )
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry(
                        'https://index.docker.io/v1/',
                        'dockerhub'
                    ) {
                        docker.image(
                            "${DOCKERHUB_REPO}:${BUILD_NUMBER}"
                        ).push()

                        docker.image(
                            "${DOCKERHUB_REPO}:${BUILD_NUMBER}"
                        ).push('latest')
                    }
                }
            }
        }
    }
}