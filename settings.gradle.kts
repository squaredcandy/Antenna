dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        jcenter() // Warning: this repository is going to shut down soon
        gradlePluginPortal()
    }
}
rootProject.name = "Antenna"
include(":app")
