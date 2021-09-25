package com.squaredcandy.antenna.data.device

import com.squaredcandy.antenna.domain.device.Device
import com.squaredcandy.antenna.domain.util.DeviceRepository
import kotlinx.coroutines.flow.StateFlow

class RealDeviceRepository(
    private val deviceFlowDataSource: DeviceFlowDataSource,
) : DeviceRepository {

    override fun getDeviceListStateFlow(): StateFlow<List<Device>> = deviceFlowDataSource.stateFlow

    override suspend fun addDevice(newDevice: Device) {
        deviceFlowDataSource.updateData { deviceList ->
            deviceList + newDevice
        }
    }

    override suspend fun modifyDevice(idToReplace: Long, deviceToReplaceWith: Device) {
        deviceFlowDataSource.updateData { deviceList ->
            return@updateData deviceList.map { device ->
                if (device.id == idToReplace) deviceToReplaceWith
                else device
            }
        }
    }

    override suspend fun deleteDevices(idsToRemove: List<Long>) {
        deviceFlowDataSource.updateData { deviceList ->
            return@updateData deviceList.filterNot { idsToRemove.contains(it.id) }
        }
    }
}
