import extensions.addAllUIDependencies
import extensions.addGlideDependencies


plugins {
    id(Plugins.PROJECT_MODULE)
}

android {

    buildFeatures {

        viewBinding = true
    }

}

dependencies {
    implementation(project(Kits.UI_KIT.path))

    addAllUIDependencies()

    addGlideDependencies()
}