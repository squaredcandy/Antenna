// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://www.jetbrains.com/intellij-repository/releases")
        maven("https://jetbrains.bintray.com/intellij-third-party-dependencies")
    }
    dependencies {
        classpath(Library.Android.GradleToolsArtifact)
        classpath(Library.Kotlin.GradlePluginArtifact)
        classpath(Library.Kotlin.Serialization.PluginArtifact)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.old files
    }
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}