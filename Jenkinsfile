pipeline {
    agent any

    environment {
        SONAR_SCANNER_PATH = 'C:\\Program Files\\sonar-scanner-6.2.1.4610-windows-x64\\bin'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Anant-1209/java-jenkins-pipeline-project2.git'
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    def mvnHome = tool 'Maven3' // Use the Maven tool configured in Jenkins
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        bat 'mvn clean test' // Run tests
                    }
                }
            }
        }

        stage('Run Tests and Code Coverage') {
            parallel {
                stage('Run Tests') {
                    steps {
                        script {
                            def mvnHome = tool 'Maven3' // Use the Maven tool configured in Jenkins
                            withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                                bat 'mvn test' // Run unit tests
                            }
                        }
                    }
                }
                stage('Code Coverage') {
                    steps {
                        script {
                            def mvnHome = tool 'Maven3' // Use the Maven tool configured in Jenkins
                            withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                                bat 'mvn jacoco:report' // Generate code coverage report using JaCoCo
                            }
                        }
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            environment {
                SONAR_TOKEN = credentials('java-jenkins-pipeline-project-task') // Ensure the correct credential ID
            }
            steps {
                script {
                    def mvnHome = tool 'Maven3' // Use the Maven tool configured in Jenkins
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        bat '''
                            mvn clean verify sonar:sonar \
                            -Dsonar.projectKey=java-jenkins-pipeline-project2 \
                            -Dsonar.projectName='java-jenkins-pipeline-project2' \
                            -Dsonar.host.url=http://localhost:9000 \
                            -Dsonar.login=%SONAR_TOKEN%
                        '''
                    }
                }
            }
        }

        stage('Success') {
            steps {
                echo 'Build, Test, SonarQube Analysis, and Code Coverage completed successfully!'
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed! Please check the logs.'
        }
    }
}