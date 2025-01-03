pipeline {
    agent any

    environment {
        SONAR_SCANNER_PATH = 'C:\\Program Files\\sonar-scanner-6.2.1.4610-windows-x64\\bin'
          JAVA_HOME = 'C:/Program Files/Java/jdk-17'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/amitnaik96/maven-pipeline-project.git'
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    def mvnHome = tool 'Maven3' // Use the Maven tool configured in Jenkins
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                         // Run tests
                        bat 'mvn clean install -DskipTests'

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
                SONAR_TOKEN = credentials('maven-jenkins-pipeline') // Ensure the correct credential ID
            }
            steps {
                script {
                    def mvnHome = tool 'Maven3' // Use the Maven tool configured in Jenkins
                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        bat '''
                            mvn clean verify sonar:sonar \
  -Dsonar.projectKey=maven-jenkins-pipeline \
  -Dsonar.projectName='maven-jenkins-pipeline' \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=sqp_4c571179a9fa0eec16b2914a4e4c21ac15fadfb5
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
