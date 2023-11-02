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
       /* stage('Test') {
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
        } */
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
                    sh "docker build -t balkiss7/achat:1.0 ."
                }
            }
        }
        stage('Docker Login') {
            steps {
               withCredentials([usernamePassword(credentialsId: 'dockerhubCred', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                echo "$DOCKERHUB_PASSWORD" | docker login -u balkiss7 --password-stdin

                }
            }
        }
    }
}
