pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "kmm-application"
include(":androidApp")
include(":shared")

enableFeaturePreview("VERSION_CATALOGS")