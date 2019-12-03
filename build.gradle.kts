buildscript {
    repositories {
        google()
        jcenter()
        maven("https://dl.bintray.com/apollographql/android")
    }
    dependencies {
        classpath (BuildPlugins.androidGradlePlugin)
        classpath (BuildPlugins.kotlinGradlePlugin)
        classpath (BuildPlugins.apolloGradlePlugin)
        classpath (BuildPlugins.safeArgsGradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://dl.bintray.com/apollographql/android")
    }
}

tasks {
    val clean by registering(Delete::class){
        delete(buildDir)
    }
}
