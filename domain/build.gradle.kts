plugins{
    id(BuildPlugins.kotlin)
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(project(path = ":data"))
    implementation(Libraries.koin_kotlin)
}