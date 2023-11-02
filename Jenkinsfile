pipeline {
    agent any
    stages {
        stage ("Clonning project") {
            steps {
                echo "Pulling .... "
                git  url: 'https://github.com/samarcherni/DevOpsProject.git', credentialsId: "GITHUB_CREDENTIALS", branch: "Gestion_Fournisseur"
            }
        }
        stage ("Build and clean") {
            steps {
                sh "mvn clean install"
            }
        }
        stage ("Compile") {
            steps {
                sh "mvn compile"
            }
        }
        stage ("Test"){
            steps {
                sh "mvn test"
            }
        }
        stage ("Quality test Sonar") {
                     steps {
                         sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=ons"
                     }
                 }
        stage ("Deploy") {
            steps {
                sh "mvn deploy"
            }
        }


        
    }
}


