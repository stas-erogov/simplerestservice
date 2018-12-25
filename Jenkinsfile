pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
            post {
                success {
                    echo 'Now archiving...'
                    archiveArtifacts artifacts: '**/*.jar'
                }
            }
        }
        stage('Deploy to Staging') {
            steps {
                build job: 'deploy-to-staging'
            }
        }
    }
}