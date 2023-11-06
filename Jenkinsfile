pipeline {
    agent any
    
    environment {
        DOCKERHUB_USERNAME = 'saharmili'
        SONAR_LOGIN = 'admin'
        SONAR_PASSWORD = 'devops'
    }

    stages {
        stage('Checkout Git') {
            steps {
                script {
                    git branch: 'GestionReglement',
                        url: 'https://github.com/samarcherni/DevOpsProject.git',
                        credentialsId: 'git'
                }
            }
        }

        stage('Clean Install') {
            steps {
                script {
                    sh "mvn clean"
                }
            }
        }

        stage('Maven Compile') {
            steps {
                script {
                    sh "mvn compile"
                }
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test' 
            }
        }
        stage('Static Test with Sonar') {
            steps {
                script {
                    sh "mvn sonar:sonar -Dsonar.login=${SONAR_LOGIN} -Dsonar.password=${SONAR_PASSWORD}"
                }
            }
        }

        stage('Maven deploy with Nexus') {
            steps {
                script {
                    sh "mvn deploy -DskipTests"
                }
            }
        }

        stage('Build Docker') {
            steps {
                script {
                    sh "docker build -t ${DOCKERHUB_USERNAME}/reglement:1.0 ."
                }
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                    sh "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"
                }
            }
        }

        stage('Push Docker') {
            steps {
                script {
                    sh "docker push ${DOCKERHUB_USERNAME}/reglement:1.0"
                }
            }
        }

       stage('Checkout Frontend Git') {
            steps {
                script {
                    git branch: 'main',
                        url: 'https://github.com/milisahar/frontend-devops-project.git',
                        credentialsId: 'git'
                }
            }
        }

        stage('Build Frontend Docker') {
            steps {
                script {
                    sh "docker build -t ${DOCKERHUB_USERNAME}/reglement-front:1.0 ."
                }
            }
        }

        stage('Docker Login Frontend') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                    sh "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"
                }
            }
        }

        stage('Push Docker Frontend') {
            steps {
                script {
                    sh "docker push ${DOCKERHUB_USERNAME}/reglement-front:1.0"
                }
            }
        }

        stage('Checkout Git for Docker Compose') {
            steps {
                script {
                    git branch: 'GestionReglement',
                        url: 'https://github.com/samarcherni/DevOpsProject.git',
                        credentialsId: 'git'
                }
            }
        }

        stage('Docker Compose Up') {
            steps {
                script {
                    sh "docker compose up -d"
                }
            }
        }
    }
}
