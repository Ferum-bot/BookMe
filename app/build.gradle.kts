import dependencies.Dependencies
import dependencies.KaptDependencies
import extensions.addTestDependencies

plugins{
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_ANDROID_EXTENSIONS)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.KOTLIN_ALLOPEN)
}

android {
    compileSdkVersion(Config.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId  = Config.APPLICATION_ID
        minSdkVersion(Config.MIN_SDK_VERSION)
        targetSdkVersion(Config.TARGET_SDK_VERSION)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = Config.MULTIDEX_ENABLED

        testInstrumentationRunner = Config.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        BuildTypeRelease.createOrConfig(this)
        BuildTypeDebug.createOrConfig(this)
    }

    flavorDimensions(flavor.FlavorDimensions.ENVIRONMENT)

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

    // Kotlin
    implementation(Dependencies.KOTLIN)

    // Core
    implementation(Dependencies.MULTIDEX)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.KTX)
    implementation(Dependencies.FRAGMENT_KTX)
    api(Dependencies.COROUTINES)
    api(Dependencies.COROUTINES_ANDROID)

    // Lifecycle
    implementation(Dependencies.LIFECYCLE_RUNTIME)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    kapt(KaptDependencies.LIFECYCLE_COMPILER)

    // Storage: database
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)
    kapt(KaptDependencies.ROOM_COMPILER)

    // Network: https (REST API)
    implementation(Dependencies.OKHTTP_CORE)
    implementation(Dependencies.OKHTTP_LOGGING_INTERCEPTOR)
    implementation(Dependencies.RETROFIT_CORE)
    implementation(Dependencies.RETROFIT_CONVERTER_GSON)

    // UI: Androidx presentation views
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAIN_LAYOUT)

    // UI: Images
    implementation(Dependencies.GLIDE)
    implementation(Dependencies.GLIDE_TRANSFORMATIONS)
    kapt(KaptDependencies.GLIDE_COMPILER)

    // DI
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.DAGGER_ANDROID)
    implementation(Dependencies.DAGGER_ANDROID_SUPPORT)
    kapt(KaptDependencies.DAGGER_COMPILER)
    kapt(KaptDependencies.DAGGER_ANDROID_PROCESSOR)

    // Logging
    implementation(Dependencies.TIMBER)

    // Test dependencies
    addTestDependencies()
}
