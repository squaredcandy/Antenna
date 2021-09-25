package com.squaredcandy.antenna.ui.device_list

import com.squaredcandy.antenna.domain.device.Device

sealed class DeviceListEvent {
    object AddNewDevice : DeviceListEvent()
    data class EditDevice(val device: Device) : DeviceListEvent()
}