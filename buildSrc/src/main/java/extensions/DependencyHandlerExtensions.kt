package extensions

import dependencies.TestDependencies
import dependencies.Dependencies
import dependencies.KaptDependencies
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependencyNotation: String): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.api(dependencyNotation: String): Dependency? =
    add("api", dependencyNotation)

fun DependencyHandler.kapt(dependencyNotation: String): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.testImplementation(dependencyNotation: String): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.addTestDependencies() {
    testImplementation(TestDependencies.JUNIT)
    testImplementation(TestDependencies.MOCKITO_KOTLIN)
    testImplementation(TestDependencies.POWERMOCK_API)
    testImplementation(TestDependencies.POWERMOCK_MODULE)
}

fun DependencyHandler.addAllUIDependencies() {
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.VIEW_PAGER2)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.PIN_VIEW)
    implementation(Dependencies.PAGE_INDICATOR)
}

fun DependencyHandler.addAllNetworkDependencies() {
    implementation(Dependencies.OKHTTP_CORE)
    implementation(Dependencies.OKHTTP_LOGGING_INTERCEPTOR)
    implementation(Dependencies.RETROFIT_CORE)
    implementation(Dependencies.RETROFIT_MOSHI_CONVERTER)
    implementation(Dependencies.MOSHI)
    implementation(Dependencies.MOSHI_KOTLIN)
}

fun DependencyHandler.addAllDIDependencies() {
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.DAGGER_ANDROID)
    implementation(Dependencies.DAGGER_ANDROID_SUPPORT)
    kapt(KaptDependencies.DAGGER_COMPILER)
    kapt(KaptDependencies.DAGGER_ANDROID_PROCESSOR)
}

fun DependencyHandler.addAllFirebaseDependencies() {
    implementation(platform(Dependencies.FIREBASE_BOM))
    implementation(Dependencies.FIREBASE_AUTH)
    implementation(Dependencies.FIREBASE_FIRESTORE)
    implementation(Dependencies.FIREBASE_REALTIME_DATABASE)
    implementation(Dependencies.FIREBASE_STORAGE)
}

fun DependencyHandler.addRoomDependencies() {
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)
    kapt(KaptDependencies.ROOM_COMPILER)
}

fun DependencyHandler.addLifecycleDependencies() {
    implementation(Dependencies.LIFECYCLE_RUNTIME)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.LIFECYCLE_LIVEDATA_KTX)
    implementation(Dependencies.LIFECYCLE_VIEW_MODEL)
    kapt(KaptDependencies.LIFECYCLE_COMPILER)
}

fun DependencyHandler.addBaseCoreDependencies() {
    implementation(Dependencies.MULTIDEX)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.KTX)
    implementation(Dependencies.FRAGMENT_KTX)
    api(Dependencies.COROUTINES)
    api(Dependencies.COROUTINES_ANDROID)
}

fun DependencyHandler.addGlideDependencies() {
    implementation(Dependencies.GLIDE)
    implementation(Dependencies.GLIDE_TRANSFORMATIONS)
    kapt(KaptDependencies.GLIDE_COMPILER)
}