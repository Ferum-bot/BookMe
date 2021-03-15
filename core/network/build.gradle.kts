import dependencies.Dependencies
import dependencies.KaptDependencies
import extensions.implementation

plugins {
    id(Plugins.CORE_MODULE)
}

dependencies {
    implementation(project(Core.BASE.path))

    // Core
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.LIFECYCLE_LIVEDATA_KTX)
    implementation(Dependencies.FRAGMENT_KTX)

    // DI
    implementation(Dependencies.DAGGER)
    kapt(KaptDependencies.DAGGER_COMPILER)

    // UI: Androidx presentation views
    implementation(Dependencies.MATERIAL)
}
