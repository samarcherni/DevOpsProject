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
    }
}