pipeline {
    agent any

    stages {
        stage('Checkout GIT') {
            steps {
                git branch: 'GestionSecteurActivites',
                    credentialsId: 'Git',
                    url: 'https://github.com/samarcherni/DevOpsProject.git'
            }
        }

        stage('Nettoyage du projet avec Maven') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('Compilation du projet avec Maven') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test' 
            }
        }
        stage('Quality test SONARQUBE') {
            steps {
        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
      }
    }
        stage('Deploy to Nexus Repository') {
            steps {
                sh 'mvn deploy'
            }
        }
         stage('Docker-build') {
            steps {
                sh 'docker build -t safwenkh/achat:1.0 .'
            }
        }
    
    stage('Docker push') {
            steps {
                sh 'docker login -u safwenkh -p Docker42426'
                sh 'docker push safwenkh/achat:1.0'
            }
        }
        stage('Checkout Frontend Git') {
            steps {
                script {
                    git branch: 'main',
                        url: 'https://github.com/Safweno/FrontDevops.git',
                        credentialsId: 'Git'
                }
            }
        }

        stage('Build Frontend Docker') {
            steps {
                script {
                    sh "docker build -t safwenkh/front:1.0 ."
                }
            }
        }

        stage('Push Docker Frontend') {
            steps {
                script {
                    sh 'docker login -u safwenkh -p Docker42426'
                    sh "docker push safwenkh/front:1.0"
                }
            }
        }

        stage('Checkout Git for Docker Compose') {
            steps {
                script {
                    git branch: 'GestionSecteurActivites',
                    credentialsId: 'Git',
                    url: 'https://github.com/samarcherni/DevOpsProject.git'
                }
            }
        }
    stage('Docker compose') {
            steps {
                sh 'docker compose up -d'
                
            }
        }
    }
}
