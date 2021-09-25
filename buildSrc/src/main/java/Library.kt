object Library {

    object BuildType {
        const val Dev = "dev"
        const val Debug = "debug"
        const val Release = "release"
    }

    object Android {
        const val ApplicationId = "com.squaredcandy.antenna"
        const val DebugApplicationSuffix = ".debug"
        const val DevApplicationSuffix = ".dev"
        const val MinCompileVersion = 26
        const val CompileVersion = 31
        const val TargetVersion = 30
        const val VersionCode = 1
        const val VersionName = "1.0"
        const val GradleVersion = "7.1.0-alpha12"
        const val JavaVersion = "11"
        const val UseIR = true
        const val UseCompose = true
        const val TestRunner = "androidx.test.runner.AndroidJUnitRunner"

        const val ApplicationPlugin = "com.android.application"
        const val GradleToolsArtifact = "com.android.tools.build:gradle:$GradleVersion"
    }

    object Kotlin {
        const val LibraryVersion = "1.5.30"
        const val AndroidPlugin = "kotlin-android"

        const val GradlePluginArtifact =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:$LibraryVersion"

        object Coroutines {
            const val Version = "1.5.1"

            const val CoreArtifact = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$Version"
            const val AndroidArtifact =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:$Version"
            const val TestArtifact = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$Version"
        }

        object Serialization {
            const val Version = "1.3.0-RC"
            const val Plugin = "kotlinx-serialization"

            const val PluginArtifact =
                "org.jetbrains.kotlin:kotlin-serialization:$LibraryVersion"
            const val JsonArtifact =
                "org.jetbrains.kotlinx:kotlinx-serialization-json:$Version"
        }

        object Reflection {
            const val Artifact = "org.jetbrains.kotlin:kotlin-reflect:$LibraryVersion"
        }
    }

    object AndroidX {
        object Activity {
            const val Version = "1.3.1"

            const val KtxArtifact = "androidx.activity:activity-ktx:$Version"
            const val ComposeArtifact = "androidx.activity:activity-compose:$Version"
        }

        object AppCompat {
            const val Version = "1.4.0-alpha03"

            const val Artifact = "androidx.appcompat:appcompat:$Version"
        }

        object Compose {
            const val Version = "1.1.0-alpha03"

            const val AnimationArtifact = "androidx.compose.animation:animation:$Version"
            const val CompilerArtifact = "androidx.compose.compiler:compiler:$Version"
            const val FoundationArtifact = "androidx.compose.foundation:foundation:$Version"
            const val MaterialArtifact = "androidx.compose.material:material:$Version"
            const val MaterialIconsArtifact =
                "androidx.compose.material:material-icons-extended:$Version"
            const val RuntimeArtifact = "androidx.compose.runtime:runtime:$Version"
            const val UiArtifact = "androidx.compose.ui:ui:$Version"
            const val UiToolingArtifact = "androidx.compose.ui:ui-tooling:$Version"
            const val UiToolingPreviewArtifact =
                "androidx.compose.ui:ui-tooling-preview:$Version"
            const val InstrumentationTestArtifact = "androidx.compose.ui:ui-test-junit4:$Version"
        }

        object Core {
            const val Version = "1.7.0-alpha01"

            const val KtxArtifact = "androidx.core:core-ktx:$Version"
        }

        object Datastore {
            const val Version = "1.0.0"

            const val CoreArtifact = "androidx.datastore:datastore-core:$Version"
        }

        object Lifecycle {
            const val ArtifactVersion = "2.4.0-alpha03"

            const val ViewModelArtifact =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:$ArtifactVersion"
            const val RuntimeArtifact =
                "androidx.lifecycle:lifecycle-runtime-ktx:$ArtifactVersion"
            const val ProcessArtifact = "androidx.lifecycle:lifecycle-process:$ArtifactVersion"
        }

        object Splashscreen {
            const val Version = "1.0.0-alpha01"

            const val Artifact = "androidx.core:core-splashscreen:$Version"
        }
    }

    object Material {
        const val Version = "1.5.0-alpha03"

        const val Artifact = "com.google.android.material:material:$Version"
    }

    object Accompanist {
        /**
         * Using old version for preview to work
         */
        const val Version = "0.18.0"

        const val SwipeRefreshArtifact =
            "com.google.accompanist:accompanist-swiperefresh:$Version"
        const val PlaceholderMaterialArtifact =
            "com.google.accompanist:accompanist-placeholder-material:$Version"
        const val PagerArtifact = "com.google.accompanist:accompanist-pager:$Version"
        const val SystemUiControllerArtifact =
            "com.google.accompanist:accompanist-systemuicontroller:$Version"
        const val InsetsArtifact = "com.google.accompanist:accompanist-insets:$Version"
    }

    object Koin {
        const val Version = "3.1.2"

        const val CoreArtifact = "io.insert-koin:koin-core:$Version"
        const val AndroidArtifact = "io.insert-koin:koin-android:$Version"
        const val TestArtifact = "io.insert-koin:koin-test-junit4:$Version"
    }

    object Voyager {
        const val Version = "1.0.0-beta10"
        const val Artifact = "cafe.adriel.voyager:voyager-navigator:$Version"
        const val KoinArtifact = "cafe.adriel.voyager:voyager-koin:$Version"
        const val TransitionArtifact = "cafe.adriel.voyager:voyager-transitions:$Version"
        const val BottomSheetArtifact = "cafe.adriel.voyager:voyager-bottom-sheet-navigator:$Version"
    }

    object Logcat {
        const val Version = "0.1"

        const val Artifact = "com.squareup.logcat:logcat:$Version"
    }

    object Leakcanary {
        const val Version = "2.7"

        const val Artifact = "com.squareup.leakcanary:leakcanary-android:$Version"
    }
}
