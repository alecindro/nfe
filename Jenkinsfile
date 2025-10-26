pipeline {
    agent any
    tools {
        maven '3.3.9'
        jdk '11'
    }
    stages {
        stage('build'){
            steps {
                script {
                 withCredentials([string(credentialsId: 'token_github', variable: 'TOKEN_GITHUB')]) {
                                    sh 'mvn clean install -DskipTests=true  -s  /opt/settings.xml -DTOKEN_GITHUB=$TOKEN_GITHUB'
                                }
                }
            }
        }
        stage('Deploy'){
            steps {
                script {
                 withCredentials([string(credentialsId: 'token_github', variable: 'TOKEN_GITHUB')]) {
                                   sh 'mvn deploy  -s  /opt/settings.xml -DTOKEN_GITHUB=$TOKEN_GITHUB'
                                }
                }
            }
        }
    }
}
