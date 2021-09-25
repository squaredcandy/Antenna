package com.squaredcandy.antenna.data.device

import androidx.datastore.core.DataStore
import com.squaredcandy.antenna.domain.device.CoroutineTools
import com.squaredcandy.antenna.domain.device.Device
import com.squaredcandy.antenna.domain.util.FlowDataSource
import kotlinx.coroutines.flow.stateIn

class DeviceFlowDataSource(
    coroutineTools: CoroutineTools,
    private val deviceDataStore: DataStore<List<Device>>,
) : FlowDataSource<List<Device>> {
    override val stateFlow = deviceDataStore.data
        .stateIn(
            coroutineTools.appCoroutineScope,
            coroutineTools.sharingStarted,
            listOf()
        )

    override suspend fun updateData(transform: suspend (t: List<Device>) -> List<Device>) {
        deviceDataStore.updateData(transform)
    }
}