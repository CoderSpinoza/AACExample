import org.gradle.kotlin.dsl.`kotlin-dsl`

buildscript {

    repositories {
        jcenter()
        google()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:3.5.2")
    }
}

plugins {
    `kotlin-dsl`
}
repositories {
    jcenter()
    google()
}