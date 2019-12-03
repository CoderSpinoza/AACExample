plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
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
    implementation(project(":domain"))
    implementation(Deps.okhttp)
    api(Deps.retrofit)
    implementation(Deps.retrofitRx)
    implementation(Deps.retrofitSerial)
    implementation(Deps.kotlinxSerial)
    implementation(Deps.loggingInterceptor)
    testImplementation(Deps.mockwebserver)

    api(Deps.room)
    api(Deps.roomRxjava)
    api(Deps.roomKtx)

    "kapt"(Deps.roomCompiler)

    testImplementation(Deps.jupiterApi)
    testImplementation(Deps.jupiterEngine)
    testRuntimeOnly(Deps.jupiterEngine)

    testRuntimeOnly(Deps.vintageEngine)

}