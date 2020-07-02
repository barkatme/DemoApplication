object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "4.0.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val kotlinSerializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val kotlin = "kotlin"
    const val serialization = "org.jetbrains.kotlin.plugin.serialization"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
}