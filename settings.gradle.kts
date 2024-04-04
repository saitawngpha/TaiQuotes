pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { setUrl("https://artifactory.appodeal.com/appodeal")}
        maven { setUrl("https://jitpack.io")}
        maven { setUrl("https://plugins.gradle.org/m2/")}
        maven { setUrl("https://maven.google.com")}
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://artifactory.appodeal.com/appodeal")}
        maven { setUrl("https://jitpack.io")}
        maven { setUrl("https://plugins.gradle.org/m2/")}
        maven { setUrl("https://maven.google.com")}
    }
}

rootProject.name = "TaiRightWords"
include(":app")
