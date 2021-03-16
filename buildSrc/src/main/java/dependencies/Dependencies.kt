package dependencies

object Dependencies {
    // Kotlin
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KOTLIN}"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"

    // Core
    const val MULTIDEX = "androidx.multidex:multidex:${Versions.MULTIDEX}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val KTX = "androidx.core:core-ktx:${Versions.KTX}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"

    // Lifecycle
    const val LIFECYCLE_LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime:${Versions.LIFECYCLE}"
    const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:${Versions.LIFECYCLE}"

    // Storage: database
    const val ROOM = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"

    // Network: https (REST API), websocket
    const val OKHTTP_CORE = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
    const val RETROFIT_CORE = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"

    // UI: Androidx presentation views
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val CONSTRAIN_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAIN_LAYOUT}"

    // UI: Images
    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_TRANSFORMATIONS = "jp.wasabeef:glide-transformations:${Versions.GLIDE_TRANSFORMATIONS}"

    // DI
    const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER}"
    const val DAGGER_ANDROID = "com.google.dagger:dagger-android:${Versions.DAGGER}"
    const val DAGGER_ANDROID_SUPPORT = "com.google.dagger:dagger-android-support:${Versions.DAGGER}"

    // Logging
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"

    // Firebase
    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE}"
    const val FIREBASE_AUTH = "com.google.firebase:firebase-auth-ktx:${Versions.FIREBASE}"

    // Google Play Services
    const val GOOGLE_PLAY_SERVICES_AUTH = "com.google.android.gms:play-services-auth:${Versions.GOOGLE_PLAY_SERVICES}"
}
