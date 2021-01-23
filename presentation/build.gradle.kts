import Libraries.androidXCamera
import Libraries.fuel
import Libraries.glide
import Libraries.koinAndroid

plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.googleServicesPlugin)
    id(BuildPlugins.firebaseCrashlytics)
    id(BuildPlugins.firebasePerformance)
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
        applicationId = "com.barkatme.demo"
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

    implementation(platform(Libraries.firebaseBom))
    implementation(Libraries.firebaseAnalytics)
    implementation(Libraries.firebaseCrashlytics)
    implementation(Libraries.firebasePerformance)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

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
    implementation(Libraries.androidxNavigationCompose)
    implementation(Libraries.appCompat)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.recyclerView)
    implementation(Libraries.recyclerViewSelection)
}