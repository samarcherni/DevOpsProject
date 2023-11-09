def notifySuccess() {   
            emailext body: "YEEEEY, The Jenkins job was successful.
                subject: "Jenkins Job - Success",
                to: 'chernisamar98@gmail.com'
}

def notifyFailure() {
            emailext body: "OUUUPS, The Jenkins job failed.
                subject: "Jenkins Job - Failure",
                to: 'chernisamar98@gmail.com'
        }


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
    
    stage('Docker build'){
     steps{
      sh 'docker build -t samarcherni/samardevopsb:spring --network host .'
     }
    }

    stage('Docker push image'){
     steps{
      sh 'docker login -u samarcherni -p Handsoff2021'
      sh 'docker push samarcherni/samardevopsb:spring'
     }
    }
    stage('Docker-Compose Deploying'){
      steps{

        sh 'docker compose up -d --remove-orphans'
      }
    }
    stage('Email Notification') {
            steps {
                script {
                    currentBuild.resultIsBetterOrEqualTo('SUCCESS') ? notifySuccess() : notifyFailure()
                }
            }
        }
}
}
