package com.squaredcandy.antenna.util

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.squaredcandy.antenna.compose.AntennaTheme

fun ComponentActivity.setContentEdgeToEdge(
    content: @Composable () -> Unit,
) {
    WindowCompat.setDecorFitsSystemWindows(window, false)

    setContent {
        AntennaTheme {
            ProvideWindowInsets {
                content()
            }
        }
    }
}