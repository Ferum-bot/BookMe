package extensions

import dependencies.TestDependencies
import dependencies.Dependencies
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependencyNotation: String): Dependency? =
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