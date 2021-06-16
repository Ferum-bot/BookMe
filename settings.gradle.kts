include(
    ":app",
    ":core:base",
    ":core:network",
    ":core:presentation"
)
include(":core:presentation")
include(":core:network")
include(":RoundCloudsView")
include(":BoxProgressBar")
include(":NestedScrollableHost")
rootProject.name = "BookMe"
rootProject.buildFileName = "build.gradle.kts"
