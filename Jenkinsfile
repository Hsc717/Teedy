pipeline {
    agent any

    environment {
        // Docker Hub credentials ID stored in Jenkins
        DOCKER_HUB_CREDENTIALS = 'dockerhub_credentials'
        // Docker Hub repository - change xx to your Docker Hub username
        DOCKER_IMAGE = 'xx/teedy-app'
        // Use build number as tag
        DOCKER_TAG = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Clean') {
            steps {
                bat 'mvn clean'
            }
        }
        stage('Compile') {
            steps {
                bat 'mvn compile'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test -Dmaven.test.failure.ignore=true'
            }
        }
        stage('PMD') {
            steps {
                bat 'mvn pmd:pmd'
            }
        }
        stage('JaCoCo') {
            steps {
                bat 'mvn jacoco:report'
            }
        }
        stage('Javadoc') {
            steps {
                bat 'mvn javadoc:javadoc -Dmaven.javadoc.failOnError=false'
            }
        }
        stage('Site') {
            steps {
                bat 'mvn site'
            }
        }
        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }
        // ===== Docker stages for Practice10 =====
        stage('Building image') {
            steps {
                script {
                    // Build Docker image from Dockerfile at project root
                    docker.build("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}")
                }
            }
        }
        stage('Upload image') {
            steps {
                script {
                    // Sign in to Docker Hub and push image
                    docker.withRegistry('https://registry.hub.docker.com', DOCKER_HUB_CREDENTIALS) {
                        docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push()
                        // Also tag as 'latest'
                        docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push('latest')
                    }
                }
            }
        }
        stage('Run containers') {
            steps {
                script {
                    // Stop and remove existing containers (if any)
                    sh 'docker stop teedy-container-8082 || true'
                    sh 'docker rm teedy-container-8082 || true'
                    sh 'docker stop teedy-container-8083 || true'
                    sh 'docker rm teedy-container-8083 || true'
                    sh 'docker stop teedy-container-8084 || true'
                    sh 'docker rm teedy-container-8084 || true'

                    // Run 3 containers on ports 8082, 8083, 8084 as per Practice10 spec
                    docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run(
                        '--name teedy-container-8082 -d -p 8082:8080'
                    )
                    docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run(
                        '--name teedy-container-8083 -d -p 8083:8080'
                    )
                    docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run(
                        '--name teedy-container-8084 -d -p 8084:8080'
                    )
                    // List all running teedy containers
                    sh 'docker ps --filter "name=teedy-container"'
                }
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: '**/target/site/**/*.*', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.jar', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.war', fingerprint: true
            junit '**/target/surefire-reports/*.xml'
        }
    }
}