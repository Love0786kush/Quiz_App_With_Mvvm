// Top-level build.gradle.kts

buildscript {
    dependencies {
        val navVersion = "2.8.9"
        // ☑️ Use the AndroidX plugin (not the old android.arch one)
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}

