import Libraries.fuel

repositories {
    jcenter()
}

plugins{
    id(BuildPlugins.kotlin)
    id(BuildPlugins.serialization)
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.coroutines)
    implementation(Libraries.kotlinxSerialization)
    fuel()
}