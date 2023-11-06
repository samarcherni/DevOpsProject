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
    }
}
