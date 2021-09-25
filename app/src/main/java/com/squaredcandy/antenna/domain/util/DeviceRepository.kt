package com.squaredcandy.antenna.domain.util

import com.squaredcandy.antenna.domain.device.Device
import kotlinx.coroutines.flow.StateFlow

interface DeviceRepository : Repository {
    fun getDeviceListStateFlow(): StateFlow<List<Device>>
    suspend fun addDevice(newDevice: Device)
    suspend fun modifyDevice(idToReplace: Long, deviceToReplaceWith: Device)
    suspend fun deleteDevices(idsToRemove: List<Long>)
}