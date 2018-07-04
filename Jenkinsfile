node {
    stage 'Checkout' 
        checkout scm
        stage 'Build test'
        sh 'gradle wrapper'

    stage 'Test'
        sh  './gradlew connectAndroidTest'
    }