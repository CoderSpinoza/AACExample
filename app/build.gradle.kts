plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    buildToolsVersion(Versions.buildToolsVersion)
    defaultConfig {
        applicationId = "com.kanghara.riiid"
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.60")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.recyclerview:recyclerview:1.1.0")

    implementation(project(":domain"))
    implementation(project(":data"))

    implementation("org.koin:koin-android:${Versions.koin}")
    implementation("org.koin:koin-android-viewmodel:${Versions.koin}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0-rc02")
    implementation("androidx.paging:paging-runtime:2.1.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.1.0")
    implementation("com.google.android.material:material:1.2.0-alpha02")

    implementation(Deps.navFragment)
    implementation(Deps.navUi)
    implementation(Deps.rxAndroid)

    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    debugImplementation("com.amitshekhar.android:debug-db:1.0.6")
}

tasks.withType < org.jetbrains.kotlin.gradle.tasks.KotlinCompile > {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
