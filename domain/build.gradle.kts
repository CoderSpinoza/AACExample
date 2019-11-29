plugins {
    kotlin("jvm")
}
dependencies {
    implementation(Deps.kotlin)
    api(Deps.rxJava)
    api(Deps.rxKotlin)
    api(project(":entities"))
}
