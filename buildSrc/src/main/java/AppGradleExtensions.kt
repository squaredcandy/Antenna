
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion

fun BaseAppModuleExtension.commonAndroid() {
    defaultConfig()
    buildTypes()
    compileOptions()
}

fun BaseAppModuleExtension.defaultConfig() {
    compileSdk = Library.Android.CompileVersion

    defaultConfig {
        applicationId = Library.Android.ApplicationId
        versionCode = Library.Android.VersionCode
        versionName = Library.Android.VersionName
        minSdk = Library.Android.MinCompileVersion
        targetSdk = Library.Android.TargetVersion
        testInstrumentationRunner = Library.Android.TestRunner
        signingConfig = signingConfigs.getByName(Library.BuildType.Debug)

        vectorDrawables {
            useSupportLibrary = true
        }
    }
}

fun BaseAppModuleExtension.composeFeatures() {
    buildFeatures {
        compose = Library.Android.UseCompose
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Library.AndroidX.Compose.Version
        kotlinCompilerVersion = Library.Kotlin.LibraryVersion
    }
}

fun BaseAppModuleExtension.buildTypes() {
    buildTypes {
        create(Library.BuildType.Dev) {
            isDebuggable = true
            applicationIdSuffix = Library.Android.DevApplicationSuffix
        }
        named(Library.BuildType.Debug) {
            isDebuggable = true
            applicationIdSuffix = Library.Android.DebugApplicationSuffix
        }
        named(Library.BuildType.Release) {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }
}

fun BaseAppModuleExtension.compileOptions() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

fun BaseAppModuleExtension.packagingOptions() {
    /**
     * Resolve Packaging conflicts that arise when using instrumentation testing
     */
    packagingOptions {
        resources {
            excludes.addAll(
                listOf(
                    "META-INF/AL2.0",
                    "META-INF/LGPL2.1",
                )
            )
        }
    }
}