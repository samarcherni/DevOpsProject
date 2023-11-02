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

        stage('Lister la version actuelle') {
            steps {
                script {
                    currentVersion = sh(script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStatus: true).trim()
                    echo "Version actuelle : ${currentVersion}"
                }
            }
        }

        stage('Incrémenter la version') {
            steps {
                script {
                    def parts = currentVersion.tokenize('.')
                    def major = parts[0].toInteger()
                    def minor = parts[1].toInteger()
                    def patch = parts[2].toInteger()

                    // Incrémenter la version mineure
                    minor++

                    // Réassembler les parties en une nouvelle version
                    def newVersion = "${major}.${minor}.${patch}"

                    // Mettre à jour la version dans le fichier pom.xml
                    sh "mvn versions:set -DnewVersion=${newVersion}"

                    // Afficher la nouvelle version
                    echo "Nouvelle version : ${newVersion}"
                }
            }

        stage ("Deploy") {
            steps {
                sh "mvn deploy"
            }
        }


        
    }
}


