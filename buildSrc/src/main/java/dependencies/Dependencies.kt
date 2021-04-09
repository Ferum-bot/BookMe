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
    const val LIFECYCLE_VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
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

    // UI: ViewPager2
    const val VIEW_PAGER2 = "androidx.viewpager2:viewpager2:${Versions.VIEW_PAGER2}"

    // UI: Pin View
    const val PIN_VIEW = "io.github.chaosleung:pinview:${Versions.PIN_VIEW}"

    // UI: Page indicator for view pager
    const val PAGE_INDICATOR = "com.romandanylyk:pageindicatorview:${Versions.PAGE_INDICATOR}"

    // Navigation Component
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"

    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    // DI
    const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER}"
    const val DAGGER_ANDROID = "com.google.dagger:dagger-android:${Versions.DAGGER}"

    const val DAGGER_ANDROID_SUPPORT = "com.google.dagger:dagger-android-support:${Versions.DAGGER}"

    // Logging
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"

    // Firebase
    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE}"
    const val FIREBASE_AUTH = "com.google.firebase:firebase-auth-ktx"
    const val FIREBASE_STORAGE = "com.google.firebase:firebase-storage-ktx"
    const val FIREBASE_REALTIME_DATABASE = "com.google.firebase:firebase-database-ktx"
    const val FIREBASE_FIRESTORE = "com.google.firebase:firebase-firestore-ktx"


    // Google Play Services
    const val GOOGLE_PLAY_SERVICES_AUTH = "com.google.android.gms:play-services-auth:${Versions.GOOGLE_PLAY_SERVICES}"

    // Facebook SDK
    const val FACEBOOK_ANDROID_SDK = "com.facebook.android:facebook-android-sdk:${Versions.FACEBOOK_SDK}"
}
