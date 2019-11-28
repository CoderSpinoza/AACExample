plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        testInstrumentationRunner = "androidx.test.ext.junit.runners.AndroidJunit4"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}

dependencies {
    implementation(project(":entities"))
    implementation(project(":domain"))
    implementation(Deps.okhttp)
    implementation(Deps.retrofit)
    implementation(Deps.retrofitRx)
    implementation(Deps.retrofitSerial)
    implementation(Deps.kotlinxSerial)
    implementation(Deps.loggingInterceptor)
    testImplementation(Deps.mockwebserver)

    testImplementation(Deps.jupiterApi)
    testImplementation(Deps.jupiterEngine)
    testRuntimeOnly(Deps.jupiterEngine)

    testRuntimeOnly(Deps.vintageEngine)

}