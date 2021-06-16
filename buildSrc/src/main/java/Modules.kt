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

enum class CustomView(val path: String) {
    ROUND_CLOUDS_VIEW(":RoundCloudsView"),
    BOX_PROGRESS_BAR(":BoxProgressBar"),
    NESTED_SCROLLABLE_HOST(":NestedScrollableHost")
}
