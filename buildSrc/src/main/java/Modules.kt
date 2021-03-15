enum class Application(val path: String) {
    APP(":app"),
}

enum class Feature(val path: String) {
}

enum class Domain(val path: String) {
}

enum class Core(val path: String) {
    BASE(":core:base"),
    NETWORK(":core:network"),
    PRESENTATION(":core:presentation")
}
