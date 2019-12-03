const val kotlinVersion = "1.3.50"
const val apollo = "1.2.1"
const val navigation = "2.1.0"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "3.5.1"
    }

    const val androidGradlePlugin       = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin        = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val apolloGradlePlugin        = "com.apollographql.apollo:apollo-gradle-plugin:$apollo"
    const val safeArgsGradlePlugin      = "androidx.navigation:navigation-safe-args-gradle-plugin:$navigation"

    const val androidApplication        = "com.android.application"
    const val kotlinAndroid             = "kotlin-android"
    const val kotlinAndroidExtensions   = "kotlin-android-extensions"
    const val apolloPlugin              = "com.apollographql.android"
    const val safeArgsPlugin            = "androidx.navigation.safeargs"
}

object AndroidSdk {
    const val min = 19
    const val compile = 29
    const val target = 29
}

object Libraries {
    private object Versions {
        const val appCompat = "1.1.0"
        const val constraintLayout = "1.1.3"
        const val ktx = "1.1.0"
        const val material = "1.0.0"
        const val recycler = "1.0.0"
        const val coroutines = "1.3.2"
        const val rxjava2 = "2.1.14"
        const val rxJavaAndroid = "2.0.2"
        const val okhttp = "3.14.1"
        const val glide = "4.9.0"
        const val lottie = "3.0.1"
        const val archLifecycle = "2.1.0-alpha02"
        const val room = "2.1.0-alpha03"
        const val cardView = "1.0.0"
        const val circleImageView = "3.0.0"
        const val dagger = "2.22.1"
    }

    const val kotlinStdLib     = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat        = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore          = "androidx.core:core-ktx:${Versions.ktx}"
    const val material         = "com.google.android.material:material:${Versions.material}"
    const val recyclerView     = "androidx.recyclerview:recyclerview:${Versions.recycler}"
    const val coroutinesCore   = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val rxjava            = "io.reactivex.rxjava2:rxjava:${Versions.rxjava2}"
    const val rxandroid         = "io.reactivex.rxjava2:rxandroid:${Versions.rxJavaAndroid}"
    const val okhttp            = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val glide             = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler     = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val apolloRuntime           = "com.apollographql.apollo:apollo-runtime:$apollo"
    const val apolloAndroidSupport    = "com.apollographql.apollo:apollo-android-support:$apollo"
    const val apolloRxjavaSupport     = "com.apollographql.apollo:apollo-rx2-support:$apollo"
    const val apolloCoroutineSupport  = "com.apollographql.apollo:apollo-coroutines-support:$apollo"
    const val apolloHttpCache         = "com.apollographql.apollo:apollo-http-cache:$apollo"
    const val lottie                  = "com.airbnb.android:lottie:${Versions.lottie}"
    const val cardView                = "androidx.cardview:cardview:${Versions.cardView}"
    const val circleImageView         = "de.hdodenhof:circleimageview:${Versions.circleImageView}"

    const val navigationFragment      = "androidx.navigation:navigation-fragment-ktx:$navigation"
    const val navigationUi            = "androidx.navigation:navigation-ui-ktx:$navigation"

    const val lifecycleViewmodel      = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.archLifecycle}"
    const val lifecycleExtension      = "androidx.lifecycle:lifecycle-extensions:${Versions.archLifecycle}"
    const val lifecycleCompiler       = "androidx.lifecycle:lifecycle-compiler:${Versions.archLifecycle}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomTesting = "androidx.room:room-testing:${Versions.room}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.12"
        const val testRunner = "1.1.1"
        const val espresso = "3.2.0"
    }
    const val junit4     = "junit:junit:${Versions.junit4}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso   = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}
