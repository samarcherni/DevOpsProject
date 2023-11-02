pipeline {
    agent any

    stages {
        
        stage('Checkout GIT') {
      steps {
        git branch: 'GestionStock',
          credentialsId: 'aziz',
          url :'https://github.com/samarcherni/DevOpsProject.git'
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
        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Azizazizaziz94'
      }
    }
    stage('Deploy to Nexus Repository') {
            steps {
                sh 'mvn deploy'
            }
        }
    stage('Docker-build') {
            steps {
                sh 'docker build -t ahmedazizdridi/achat:20 .'
            }
        }
    
    stage('Docker push') {
            steps {
                sh 'docker login -u ahmedazizdridi -p Azizazizaziz94'
                sh 'docker push ahmedazizdridi/achat:20'
            }
        }
    stage('Docker compose') {
            steps {
                sh 'docker compose up -d'
                
            }
        }
    }
}
