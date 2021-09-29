package com.squaredcandy.antenna.ui.device_list

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.squaredcandy.antenna.domain.device.CoroutineTools
import com.squaredcandy.antenna.domain.device.Device
import com.squaredcandy.antenna.domain.util.DeviceRepository
import com.squaredcandy.antenna.ui.compose.BroadcastSharedFlow
import com.squaredcandy.antenna.ui.util.SnackbarContent
import com.squaredcandy.antenna.ui.util.WakeOnLan
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeviceListScreenModel(
    private val coroutineTools: CoroutineTools,
    private val deviceRepository: DeviceRepository,
) : ScreenModel {

    private val mutableSelectedDeviceListStateFlow = MutableStateFlow<List<Long>>(listOf())

    private val deviceListFlow = deviceRepository.getDeviceListStateFlow()

    private val mutableSnackbarContentSharedFlow = BroadcastSharedFlow<SnackbarContent>()

    val viewState by lazy {
        combine(
            deviceListFlow,
            mutableSelectedDeviceListStateFlow
        ) { deviceList,
            selectedDeviceList ->

            val state = when {
                deviceList.isEmpty() -> DeviceListViewState.State.Empty
                selectedDeviceList.isNotEmpty() -> DeviceListViewState.State.Editing
                deviceList.isNotEmpty() && selectedDeviceList.isEmpty() -> DeviceListViewState.State.Populated
                else -> DeviceListViewState.State.Empty
            }

            DeviceListViewState(
                state = state,
                selectedDeviceList = selectedDeviceList,
                deviceList = deviceList,
                onAddDevice = ::onAddDevice,
                editSelected = ::editSelected,
                onWakeDevice = ::onWakeDevice,
                toggleSelected = ::toggleSelected,
                deleteAllSelected = ::deleteSelected,
                clearSelected = ::clearSelected,
            )
        }
            .stateIn(
                scope = coroutineScope,
                started = coroutineTools.sharingStarted,
                initialValue = DeviceListViewState(
                    onAddDevice = ::onAddDevice,
                    editSelected = ::editSelected,
                    onWakeDevice = ::onWakeDevice,
                    toggleSelected = ::toggleSelected,
                    deleteAllSelected = ::deleteSelected,
                    clearSelected = ::clearSelected,
                )
            )
    }

    private val mutableEvent = MutableSharedFlow<DeviceListEvent>()
    val event = mutableEvent.asSharedFlow()

    val sideEffectsFlow = mutableSnackbarContentSharedFlow
        .map {
            DeviceListSideEffect.ShowSnackbar(it)
        }

    private fun onAddDevice() {
        coroutineScope.launch {
            mutableEvent.emit(DeviceListEvent.AddNewDevice)
        }
    }

    private fun editSelected() {
        coroutineScope.launch {
            val viewState = viewState.value
            val selectedDeviceId = viewState.selectedDeviceList.firstOrNull() ?: return@launch
            val device =
                viewState.deviceList.firstOrNull { it.id == selectedDeviceId } ?: return@launch
            mutableEvent.emit(DeviceListEvent.EditDevice(device))
            mutableSelectedDeviceListStateFlow.value = listOf()
        }
    }

    private fun toggleSelected(deviceId: Long) {
        coroutineScope.launch {
            val currentList = mutableSelectedDeviceListStateFlow.value
            val newList = if (currentList.contains(deviceId)) {
                currentList - deviceId
            } else {
                currentList + deviceId
            }

            mutableSelectedDeviceListStateFlow.value = newList
        }
    }

    private fun clearSelected() {
        coroutineScope.launch {
            mutableSelectedDeviceListStateFlow.value = listOf()
        }
    }

    private fun deleteSelected() {
        coroutineScope.launch {
            deviceRepository.deleteDevices(mutableSelectedDeviceListStateFlow.value)
            mutableSelectedDeviceListStateFlow.value = listOf()
        }
    }

    private fun onWakeDevice(device: Device) {
        coroutineScope.launch {
            val result = withContext(coroutineTools.io) {
                WakeOnLan.sendSignal(device.broadcastIPAddress, device.macAddress, device.port.port)
            }
            result.fold(
                onSuccess = {
                    mutableSnackbarContentSharedFlow.emit(
                        SnackbarContent(
                            message = DeviceListStringResource.SignalSent,
                        ),
                    )
                },
                onFailure = {
                    mutableSnackbarContentSharedFlow.emit(
                        SnackbarContent(
                            message = DeviceListStringResource.FailedToSend,
                            actionLabel = DeviceListStringResource.TryAgain,
                            onActionClicked = {
                                onWakeDevice(device)
                            },
                        ),
                    )
                },
            )
        }
    }
}