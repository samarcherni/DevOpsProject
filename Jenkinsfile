pipeline {
    agent any
    stages {
        stage ("Clonning project") {
            steps {
                echo "Pulling .... "
                git  url: 'https://github.com/samarcherni/projet-DevOps.git', credentialsId: "GITHUB_CREDENTIALS", branch: "Gestion_Fournisseur"
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
        
    }
}