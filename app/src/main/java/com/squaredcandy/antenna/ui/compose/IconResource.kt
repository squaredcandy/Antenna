package com.squaredcandy.antenna.ui.compose

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext

interface IconResource {
    fun resolveIcon(resources: Resources): ImageVector
    fun resolveContentDescription(resources: Resources): String?
}

@Composable
@ReadOnlyComposable
fun IconResource.resolveIcon(): ImageVector {
    LocalConfiguration.current
    return resolveIcon(LocalContext.current.resources)
}

@Composable
@ReadOnlyComposable
fun IconResource.resolveContentDescription(): String? {
    LocalConfiguration.current
    return resolveContentDescription(LocalContext.current.resources)
}