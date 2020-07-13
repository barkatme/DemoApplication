import org.gradle.api.artifacts.dsl.DependencyHandler

const val kotlinVersion = "1.3.72"

object Libraries {
    private object Versions {
        const val jetpack = "1.1.0"
        const val constraintLayout = "1.1.3"
        const val ktx = "1.3.0"
        const val ktxFragment = "1.2.5"
        const val coroutines = "1.3.7"
        const val fuel = "2.2.3"
        const val serialization = "0.20.0"
        const val lifecycle = "2.2.0"
        const val koin = "2.1.5"
        const val recyclerview = "1.1.0"
        const val recyclerViewSelection = "1.1.0-rc01"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:" +
            Versions.constraintLayout
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.ktxFragment}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:" +
            Versions.coroutines
    const val fuelPrefix = "com.github.kittinunf.fuel"
    const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:" +
            Versions.serialization

    // Koin for Kotlin
    const val koinKotlin = "org.koin:koin-core:${Versions.koin}"
    // Koin for Android
    private const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    // or Koin for Lifecycle scoping
    private const val koinAndroidx = "org.koin:koin-androidx-scope:${Versions.koin}"
    // or Koin for Android Architecture ViewModel
    private const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    // or Koin for Android Fragment Factory (unstable version)
    private const val koinFragment = "org.koin:koin-androidx-fragment:${Versions.koin}"

    const val androidxLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val androidxViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val recyclerViewSelection = "androidx.recyclerview:recyclerview-selection:" +
            Versions.recyclerViewSelection

    fun DependencyHandler.koinAndroid() {
        implementation(koinAndroid)
        implementation(koinAndroidx)
        implementation(koinViewModel)
        implementation(koinFragment)
    }

    fun DependencyHandler.fuel() {
        arrayOf("fuel", "fuel-coroutines", "fuel-kotlinx-serialization").forEach {
            implementation("$fuelPrefix:$it:${Versions.fuel}")
        }
    }
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.12"
        const val testRunner = "1.1.0-alpha4"
        const val espresso = "3.1.0-alpha4"
    }

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}