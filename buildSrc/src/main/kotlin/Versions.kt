/**
 * @author kevin.kang. Created on 2019-11-28..
 */
object Versions {
    const val gradle = "3.5.2"
    const val kotlin = "1.3.60"
    const val retrofit = "2.6.2"
    const val okhttp = "4.2.2"
    const val junit = "5.1.1"
    const val koin = "2.0.1"

    const val minSdkVersion = 19
    const val compileSdkVersion = 29
    const val buildToolsVersion = "29.0.2"
    const val targetSdkVersion = 29

}

object Deps {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlinxSerial = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.14.0"

    const val rxJava = "io.reactivex.rxjava2:rxjava:2.2.13"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitRx = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val retrofitSerial =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.4.0"
    const val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    const val jupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
    const val jupiterParams = "org.junit.jupiter:junit-jupiter-params:${Versions.junit}"
    const val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
    const val vintageEngine = "org.junit.vintage:junit-vintage-engine:${Versions.junit}"
}