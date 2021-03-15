import dependencies.Dependencies
import dependencies.KaptDependencies
import extensions.api
import extensions.implementation
import extensions.kapt

plugins {
    id(Plugins.CORE_MODULE)
}

dependencies {
    // Core
    implementation(Dependencies.APPCOMPAT)
    api(Dependencies.COROUTINES)
    api(Dependencies.COROUTINES_ANDROID)

    // DI
    implementation(Dependencies.DAGGER)
    kapt(KaptDependencies.DAGGER_COMPILER)
}
