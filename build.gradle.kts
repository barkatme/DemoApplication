// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinGradlePlugin)
        classpath(BuildPlugins.kotlinSerializationPlugin)
        classpath(BuildPlugins.googleServices)
        classpath(BuildPlugins.firebaseCrashlyticsClasspath)
        classpath(BuildPlugins.firebasePerformanceClasspath)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean").configure {
    delete("build")
}