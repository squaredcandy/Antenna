package com.squaredcandy.antenna.root

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.squaredcandy.antenna.device_list.DeviceListScreen

object RootScreen : Screen {
    @Composable
    override fun Content() {
        Navigator(DeviceListScreen)
    }
}