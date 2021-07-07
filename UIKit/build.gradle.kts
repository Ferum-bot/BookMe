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

    addAllUIDependencies()

    addGlideDependencies()
}