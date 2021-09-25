package com.squaredcandy.antenna.ui.root

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.squaredcandy.antenna.ui.device_list.DeviceListScreen

object RootScreen : Screen {
    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun Content() {
        Navigator(DeviceListScreen) { navigator ->
            FadeTransition(navigator = navigator)
        }
    }
}