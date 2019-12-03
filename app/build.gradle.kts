plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.apolloPlugin)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.safeArgsPlugin)
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = "com.android.pokemon"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true

        javaCompileOptions {
            annotationProcessorOptions {
                includeCompileClasspath = true
            }
        }

    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dataBinding {
        isEnabled = true
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.ktxCore)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.recyclerView)
    implementation(Libraries.material)
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutinesAndroid)
    implementation(Libraries.okhttp)
    implementation(Libraries.apolloRuntime)
    implementation(Libraries.apolloAndroidSupport)
    implementation(Libraries.apolloCoroutineSupport)
    implementation(Libraries.apolloHttpCache)
    implementation(Libraries.glide)
    annotationProcessor(Libraries.glideCompiler)
    implementation(Libraries.lottie)
    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUi)

    implementation(Libraries.lifecycleExtension)
    implementation(Libraries.lifecycleViewmodel)
    implementation(Libraries.lifecycleCompiler)

    implementation("androidx.multidex:multidex:2.0.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    compileOnly("javax.annotation:javax.annotation-api:1.3.2")
    compileOnly("org.jetbrains:annotations:13.0")

    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.testRunner)
    androidTestImplementation(TestLibraries.espresso)
}
