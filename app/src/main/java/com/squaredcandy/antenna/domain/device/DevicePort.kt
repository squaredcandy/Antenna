package com.squaredcandy.antenna.domain.device

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.squaredcandy.antenna.R

enum class DevicePort(val port: Int) {
    SEVEN(7),
    NINE(9),
    ;

    fun resolveString(resources: Resources): String {
        return when (this) {
            SEVEN -> resources.getString(R.string.device_port_7)
            NINE -> resources.getString(R.string.device_port_9)
        }
    }
}

@Composable
@ReadOnlyComposable
fun DevicePort.resolveString(): String {
    LocalConfiguration.current
    return resolveString(LocalContext.current.resources)
}