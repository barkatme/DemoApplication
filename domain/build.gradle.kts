plugins{
    id("kotlin")
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(project(path = ":data"))
}