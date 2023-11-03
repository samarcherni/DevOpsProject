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

        stage ("Build docker image") {
                    steps {
                        sh 'docker build -t onsbha/achat:1.2.0 .'
                    }
            }
        stage ("Push docker image") {
                    steps {
                        sh 'docker login -u onsbha -p B*z2d6Hgf9wvMW#'
                        sh 'docker push onsbha/achat:1.2.0'
                    }
            }
        stage ("Docker-Compose") {
                    steps {
                        sh 'docker compose up -d'
                    }
            }
    }
}


