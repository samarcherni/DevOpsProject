pipeline {
    agent any
    environment {
        DOCKERHUB_USERNAME = 'saharmili'
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
        /*stage('Test') {
            steps {
                sh 'mvn test' 
            }
        }*/
        stage('Static Test with Sonar') {
            steps {
                script {
                    sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=devops"
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
                    sh "docker build -t saharmili/reglement:1.0 ."
                }
            }
        }
        stage('Docker Login') {
           steps{
                withCredentials([usernamePassword(credentialsId: 'docker', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                sh "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"
             }
            }

        }
       stage('push Docker') {
            steps {
                script {
                    sh "docker push ${DOCKERHUB_USERNAME}/reglement:1.0"
                }
            }
        } /*
         stage('Docker Compose') {
            steps {
                script {
                    sh "docker compose up -d"
                }
            }
        } */
    }
}
