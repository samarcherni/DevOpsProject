pipeline {
  agent any

  stages {
    stage('Checkout GIT') {
      steps {
        git branch: 'GestionCategorieProduit',
          credentialsId: '37b47f1c-2237-4c2d-93f3-47a063dd3226',
          url :'https://github.com/samarcherni/DevOpsProject.git'
      }
     
    }
    stage('Clean Build'){
            steps{
                sh 'mvn clean'
            }
        }
    stage('Compile'){
            steps{
                sh 'mvn compile'
            }
        }
    stage('Mockito Tests') {
     steps {
      sh 'mvn clean test -Pmockito-tests'
        }
        post {
          always {
            junit(
              allowEmptyResults: true,
              testResults: 'target/surefire-reports/**/*.xml'
               )
             }
            }
        }
    stage('Quality test SONARQUBE') {
      steps {
        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=devops'
      }
    }

    stage('Deploy artifact with Nexus') {
      steps {
        sh 'mvn deploy -DskipTests'
      }
    }
  
}
}
