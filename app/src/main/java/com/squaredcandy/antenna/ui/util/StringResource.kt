package com.squaredcandy.antenna.ui.util

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext

interface StringResource {
    fun resolve(resources: Resources): String
}

@Composable
@ReadOnlyComposable
fun StringResource.resolve(): String {
    LocalConfiguration.current
    return resolve(LocalContext.current.resources)
}