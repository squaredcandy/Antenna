package com.squaredcandy.antenna.compose

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.view.Window
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AntennaTheme(
    systemUiController: SystemUiController = rememberSystemUiController(),
    darkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = if (darkMode) AntennaColor.darkPalette else AntennaColor.lightPalette,
    ) {
        val statusBarColor = MaterialTheme.colors.background
        val navigationBarColor = MaterialTheme.colors.background

        // Update status bar colours
        val window = LocalContext.current.findWindow()
        SideEffect {
            systemUiController.setStatusBarColor(
                color = statusBarColor,
                darkIcons = !darkMode
            )

            systemUiController.setNavigationBarColor(
                color = navigationBarColor,
                darkIcons = !darkMode
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                window?.navigationBarDividerColor = navigationBarColor.toArgb()
            }
        }

        content()
    }
}

private fun Context.findWindow(): Window? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context.window
        context = context.baseContext
    }
    return null
}