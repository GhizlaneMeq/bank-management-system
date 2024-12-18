pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        DOCKER_IMAGE = 'banking-system'
        DOCKER_TAG = "${env.BUILD_NUMBER ?: 'latest'}" // Default to 'latest' if BUILD_NUMBER is not set
        SONAR_TOKEN = credentials('sonar-token') // Ensure credentials are set in Jenkins
        MAVEN_OPTS = '-Dmaven.test.failure.ignore=true'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Environment Check') {
            steps {
                sh '''
                    echo "Git version:"
                    git --version || true
                    echo "Current Git branch:"
                    git branch --show-current || true
                    echo "Java version:"
                    java -version || true
                    echo "Maven version:"
                    mvn --version || true
                    echo "Working directory contents:"
                    pwd
                    ls -la
                '''
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests --batch-mode --errors'
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test --batch-mode'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    jacoco(
                        execPattern: '**/target/*.exec',
                        classPattern: '**/target/classes',
                        sourcePattern: '**/src/main/java'
                    )
                }
            }
        }

        stage('Code Quality Analysis') {
            steps {
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                    sh '''
                        mvn sonar:sonar \
                            -Dsonar.projectKey=banking-system \
                            -Dsonar.projectName=BankingSystem \
                            -Dsonar.host.url=http://localhost:9000 \
                            -Dsonar.login=$SONAR_TOKEN
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def builtImage = docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                    builtImage.tag('latest') // Add 'latest' tag
                }
            }
        }

        stage('Manual Approval') {
            when {
                environment name: 'DEPLOY_ENV', value: 'production' // Optional: skip in non-production builds
            }
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    input message: 'Deploy to production?', ok: 'Proceed'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh '''
                        docker stop banking-system || true
                        docker rm banking-system || true
                    '''
                    docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").run('-p 8081:8080 --name banking-system')
                }
            }
        }
    }

    post {
        cleanup {
            cleanWs()
        }
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline execution failed!'
        }
    }
}
