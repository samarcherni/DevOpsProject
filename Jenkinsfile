pipeline {
    agent any
    environment {
        DOCKERHUB_USERNAME = 'balkiss7'
    }
    stages {
        stage('Checkout Git') {
            steps {
                script {
                    git branch: 'GestionOperateur',
                        url: 'https://github.com/samarcherni/DevOpsProject.git',
                        credentialsId: 'devops-classe-git' 
                    echo 'Git done'
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
                    sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=balkiss"
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
                    sh "docker build -t balkiss7/achat:11 ."
                }
            }
        }
        stage('Docker Login') {
           steps{
                withCredentials([usernamePassword(credentialsId: 'operateur', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                sh "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"
             }
            }

        }
       stage('push Docker') {
            steps {
                script {
                    sh "docker push ${DOCKERHUB_USERNAME}/achat:11"
                }
            }
        } 
        stage('Checkout Frontend Git') {
            steps {
                script {
                    git branch: 'GestionOperateur',
                        url: 'https://github.com/samarcherni/DevOpsProject-Front.git',
                        credentialsId: 'devops-classe-git'
                }
            }
        }

        stage('Build Frontend Docker') {
            steps {
                script {
                    sh "docker build -t ${DOCKERHUB_USERNAME}/operateur-front:1.0 ."
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
                    sh "docker push ${DOCKERHUB_USERNAME}/operateur-front:1.0"
                }
            }
        }

        stage('Checkout Git for Docker Compose') {
            steps {
                script {
                    git branch: 'GestionOperateur',
                        url: 'https://github.com/samarcherni/DevOpsProject.git',
                        credentialsId: 'devops-classe-git'
                }
            }
        }
         stage('Docker Compose') {
            steps {
                script {
                    sh "docker compose up -d"
                }
            }
        } 
    }
}
