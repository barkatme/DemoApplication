import Libraries.fuel

repositories {
    jcenter()
}

plugins {
    id(BuildPlugins.kotlin)
    id(BuildPlugins.serialization)
}

dependencies {
    implementation(project(path = ":domain"))
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.coroutines)
    implementation(Libraries.kotlinxSerialization)
    implementation(Libraries.koinKotlin)
    fuel()
}