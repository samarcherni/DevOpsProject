pipeline {
    agent any
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
                    sh "mvn sonar:balkiss"
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


    }
}