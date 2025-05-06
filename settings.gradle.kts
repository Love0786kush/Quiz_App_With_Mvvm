// settings.gradle.kts

pluginManagement {
    repositories {
        // Plugin resolution restricted to Google, Maven Central and Gradle Plugin Portal
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    // Disallow repositories in individual build.gradle files
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // Central repos for libraries
        google()
        mavenCentral()
    }
}

rootProject.name = "Quiz App With Mvvm"
include(":app")
