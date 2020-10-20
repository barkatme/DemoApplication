import Libraries.fuel
import Libraries.koinAndroid
import Libraries.room

repositories {
    jcenter()
}

plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.serialization)
}

android {
    compileSdkVersion(AndroidSdk.compile)
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        consumerProguardFile("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "DATABASE_NAME", "\"DemoApplicationDatabase\"")
        }
        getByName("debug") {
            buildConfigField("String", "DATABASE_NAME", "\"DemoApplicationDebugDatabase\"")
        }
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(path = ":domain"))
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.coroutines)
    implementation(Libraries.kotlinxSerialization)
    implementation(platform(Libraries.firebaseBom))
    implementation(Libraries.firebaseAnalytics)
    implementation(Libraries.firebaseCrashlytics)
    implementation(Libraries.firebasePerformance)
    koinAndroid()
    fuel()
    room()
    implementation(Libraries.androidxPaging)
    testImplementation(TestLibraries.mockito)
    testImplementation(TestLibraries.androidxTest)
}