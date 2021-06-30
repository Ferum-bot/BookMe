import extensions.addAllUIDependencies


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
}