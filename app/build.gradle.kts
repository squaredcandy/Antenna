plugins {
    id(Library.Android.ApplicationPlugin)
    id(Library.Kotlin.AndroidPlugin)
    id(Library.Kotlin.Serialization.Plugin)
    `use-opt-in`
}

android {
    commonAndroid()
    composeFeatures()
    packagingOptions()
    kotlinOptions {
        jvmTarget = Library.Android.JavaVersion
        useIR = Library.Android.UseIR
    }
}

dependencies {

    implementation(Library.Kotlin.Coroutines.CoreArtifact)
    implementation(Library.Kotlin.Coroutines.AndroidArtifact)
    implementation(Library.Kotlin.Serialization.JsonArtifact)

    implementation(Library.AndroidX.Activity.KtxArtifact)
    implementation(Library.AndroidX.Activity.ComposeArtifact)
    implementation(Library.AndroidX.AppCompat.Artifact)
    implementation(Library.AndroidX.Core.KtxArtifact)
    implementation(Library.AndroidX.Compose.AnimationArtifact)
    implementation(Library.AndroidX.Compose.CompilerArtifact)
    implementation(Library.AndroidX.Compose.FoundationArtifact)
    implementation(Library.AndroidX.Compose.MaterialArtifact)
    implementation(Library.AndroidX.Compose.MaterialIconsArtifact)
    implementation(Library.AndroidX.Compose.RuntimeArtifact)
    implementation(Library.AndroidX.Compose.UiArtifact)
    implementation(Library.AndroidX.Compose.UiToolingArtifact)
    implementation(Library.AndroidX.Compose.UiToolingPreviewArtifact)
    implementation(Library.AndroidX.Datastore.CoreArtifact)
    implementation(Library.AndroidX.Lifecycle.ViewModelArtifact)
    implementation(Library.AndroidX.Lifecycle.RuntimeArtifact)
    implementation(Library.AndroidX.Splashscreen.Artifact)

    implementation(Library.Material.Artifact)

    //    implementation(Library.Accompanist.SwipeRefreshArtifact)
    implementation(Library.Accompanist.PlaceholderMaterialArtifact)
    implementation(Library.Accompanist.PagerArtifact)
    implementation(Library.Accompanist.SystemUiControllerArtifact)
    implementation(Library.Accompanist.InsetsArtifact)

    implementation(Library.Koin.CoreArtifact)
    implementation(Library.Koin.AndroidArtifact)

    implementation(Library.Voyager.Artifact)
    implementation(Library.Voyager.BottomSheetArtifact)

    implementation(Library.Logcat.Artifact)

    debugImplementation(Library.Leakcanary.Artifact)
}