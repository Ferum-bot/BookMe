package extensions

import dependencies.TestDependencies
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