import Libraries.androidXCamera
import Libraries.fuel
import Libraries.glide
import Libraries.koinAndroid

plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
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
        applicationId = null
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(project(path = ":data"))
    implementation(project(path = ":domain"))

    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.ktxCore)
    implementation(Libraries.fragmentKtx)

    implementation(Libraries.coroutines)
    implementation(Libraries.coroutinesAndroid)

    koinAndroid()
    fuel()
    glide()

    androidxDependencies()

    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}


fun DependencyHandlerScope.androidxDependencies() {
    androidXCamera()
    implementation(Libraries.androidxPaging)
    implementation(Libraries.androidxViewModel)
    implementation(Libraries.androidxLiveData)
    implementation(Libraries.androidxLifecycleExtensions)
    implementation(Libraries.androidxNavigationFragment)
    implementation(Libraries.androidxNavigationUi)
    implementation(Libraries.appCompat)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.recyclerView)
    implementation(Libraries.recyclerViewSelection)
}