package com.squaredcandy.antenna.ui.device_list

import com.squaredcandy.antenna.domain.device.Device

data class DeviceListViewState(
    val state: State = State.Empty,
    val deviceList: List<Device> = listOf(),
    val selectedDeviceList: List<Long> = listOf(),
    val onAddDevice: () -> Unit = {},
    val onWakeDevice: (device: Device) -> Unit = {},
    val toggleSelected: (deviceId: Long) -> Unit = {},
    val editSelected: () -> Unit = {},
    val deleteAllSelected: () -> Unit = {},
    val clearSelected: () -> Unit = {},
) {
    enum class State {
        Empty,
        Populated,
        Editing,
    }
}