// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }

    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io")}
        maven { url = uri("https://www.jitpack.io") }
    }
}


plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id ("com.google.devtools.ksp") version "1.9.21-1.0.15" apply false

}