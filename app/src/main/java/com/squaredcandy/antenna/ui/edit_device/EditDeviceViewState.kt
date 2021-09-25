package com.squaredcandy.antenna.ui.edit_device

import com.squaredcandy.antenna.domain.device.Device
import com.squaredcandy.antenna.domain.device.DevicePort

data class EditDeviceViewState(
    val isEditingDevice: Boolean = false,
    val device: Device = Device(),
    val validName: Boolean = true,
    val validMACAddress: Boolean = true,
    val validIPAddress: Boolean = true,
    val updateName: (String) -> Unit = {},
    val updateMacAddress: (String) -> Unit = {},
    val updateIPAddress: (String) -> Unit = {},
    val updatePort: (DevicePort) -> Unit = {},
    val onSaveDevice: () -> Unit = {},
    val onBack: () -> Unit = {},
)