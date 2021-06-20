import dependencies.Dependencies
import extensions.*
import org.gradle.kotlin.dsl.implementation

plugins{
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_ANDROID_EXTENSIONS)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.KOTLIN_ALLOPEN)
    id(Plugins.GOOGLE_SERVICES)
    id(Plugins.NAVIGATION_SAFE_ARGS)
    id(Plugins.FIREBASE_CRASHLYTICS)
}

android {
    compileSdkVersion(Config.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId  = Config.APPLICATION_ID
        minSdkVersion(Config.MIN_SDK_VERSION)
        targetSdkVersion(Config.TARGET_SDK_VERSION)
        versionCode = 3
        versionName = "1.0.1"
        multiDexEnabled = Config.MULTIDEX_ENABLED

        testInstrumentationRunner = Config.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        BuildTypeRelease.createOrConfig(this)
        BuildTypeDebug.createOrConfig(this)
    }

//    signingConfigs {
//        SigningConfigRelease.createOrConfig(this)
//        SigningConfigDebug.createOrConfig(this)
//    }

    flavorDimensions(flavor.FlavorDimensions.ENVIRONMENT)

    buildFeatures {
        viewBinding = true
    }

    productFlavors {
        flavor.EnvironmentFlavor.Master.createOrConfigForApp(this)
        flavor.EnvironmentFlavor.Dev.createOrConfigForApp(this)
        flavor.EnvironmentFlavor.Prerelease.createOrConfigForApp(this)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions.apply {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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

allOpen {
    annotation("com.levit.book_me.core_base.annotations.Mockable")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(Core.BASE.path))
    implementation(project(Core.NETWORK.path))
    implementation(project(Core.PRESENTATION.path))

    implementation(project(CustomView.ROUND_CLOUDS_VIEW.path))
    implementation(project(CustomView.BOX_PROGRESS_BAR.path))
    implementation(project(CustomView.NESTED_SCROLLABLE_HOST.path))

    // Kotlin
    implementation(Dependencies.KOTLIN)

    // Core
    addBaseCoreDependencies()

    // Lifecycle
    addLifecycleDependencies()

    // Storage: database
    addRoomDependencies()

    // Network: https (REST API)
    addAllNetworkDependencies()

    addAllUIDependencies()

    // UI: Images
    addGlideDependencies()

    // Navigation Component
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)

    // DI
    addAllDIDependencies()

    // Logging
    implementation(Dependencies.TIMBER)

    // Firebase
    addAllFirebaseDependencies()

    // Google Play Services
    implementation(Dependencies.GOOGLE_PLAY_SERVICES_AUTH)

    // Facebook
    implementation(Dependencies.FACEBOOK_ANDROID_SDK)

    // Pin View
    implementation(Dependencies.PIN_VIEW)

    // Page Indicator
    implementation(Dependencies.PAGE_INDICATOR)

    // Test dependencies
    addTestDependencies()
}
