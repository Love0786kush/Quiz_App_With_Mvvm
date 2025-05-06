// App-level build.gradle.kts

plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs") // ✅ Safe Args plugin
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.quizappwithmvvm"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.quizappwithmvvm"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.auth)

    // ✅ Navigation component (KTX version recommended)
    val nav_version = "2.8.9"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // ✅ Lottie animation
    implementation("com.airbnb.android:lottie:6.0.0")

    // ✅ Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    // ✅ Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
