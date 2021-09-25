package com.squaredcandy.antenna.ui.device_list

import com.squaredcandy.antenna.ui.util.SnackbarContent

sealed class DeviceListSideEffect {
    data class ShowSnackbar(val content: SnackbarContent) : DeviceListSideEffect()
}