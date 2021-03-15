@file:Suppress("UnstableApiUsage")

package commons

import BuildTypeDebug
import BuildTypeRelease
import Config
import dependencies.Dependencies
import dependencies.KaptDependencies
import extensions.addTestDependencies
import extensions.implementation
import extensions.kapt

/**
 * базовый скрипт для feature/core модулей проекта
 */

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("de.mannodermaus.android-junit5")
}

android {
    compileSdkVersion(Config.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(Config.MIN_SDK_VERSION)
    }

    buildTypes {
        getByName(BuildTypeRelease.name) {}
        getByName(BuildTypeDebug.name) {}
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    androidExtensions {
        isExperimental = true
    }

    sourceSets.forEach {
        it.java.setSrcDirs(it.java.srcDirs + "src/$it.name/kotlin")
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(Dependencies.KOTLIN)
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.TIMBER)

    kapt(KaptDependencies.DAGGER_COMPILER)

    addTestDependencies()
}
