package dependencies

object Classpath {

    const val BUILD_GRADLE_TOOLS = "com.android.tools.build:gradle:${Versions.TOOLS_GRADLE}"

    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val KOTLIN_ALL_OPEN = "org.jetbrains.kotlin:kotlin-allopen:${Versions.KOTLIN}"

    const val SAFE_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.SAFE_ARGS}"

    const val GOOGLE_SERVICE = "com.google.gms:google-services:${Versions.CLASSPATH_GOOGLE_PLAY_SERVICES}"

    const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics-gradle:${Versions.FIREBASE_CRASHLYTICS}"
}