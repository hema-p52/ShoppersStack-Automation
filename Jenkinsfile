
pipeline {
    agent any

    tools {
        maven 'Maven-3.10.1'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out project from GitHub'
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                echo 'Running Maven and TestNG tests'
                bat 'mvn clean test'
            }
        }
    }

    post {
        success {
            echo 'BUILD SUCCESSFUL!'
        }

        failure {
            echo 'BUILD FAILED!'
        }

        always {
            echo 'Pipeline execution completed'
        }
    }
}
