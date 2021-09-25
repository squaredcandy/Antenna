package com.squaredcandy.antenna.ui.device_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.squaredcandy.antenna.ui.compose.collectAsStateLifecycleAware
import com.squaredcandy.antenna.ui.edit_device.EditDeviceScreen
import kotlinx.coroutines.flow.SharedFlow

object DeviceListScreen : Screen {
    @Composable
    override fun Content() {
        val deviceListScreenModel = getScreenModel<DeviceListScreenModel>()
        val deviceListViewState by deviceListScreenModel.viewState.collectAsStateLifecycleAware()
        deviceListScreenModel.event.HandleEvent()
        DeviceListView(deviceListViewState, deviceListScreenModel.sideEffectsFlow)
    }

    @Composable
    private fun SharedFlow<DeviceListEvent>.HandleEvent() {
        val event = collectAsState(initial = null).value ?: return
        when (event) {
            DeviceListEvent.AddNewDevice -> {
                val navigator = LocalNavigator.currentOrThrow
                navigator.push(EditDeviceScreen())
            }
            is DeviceListEvent.EditDevice -> {
                val navigator = LocalNavigator.currentOrThrow
                navigator.push(EditDeviceScreen(deviceToEdit = event.device))
            }
        }
    }
}
