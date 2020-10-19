import Libraries.fuel

plugins{
    id(BuildPlugins.kotlin)
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.koinKotlin)
    fuel()
}