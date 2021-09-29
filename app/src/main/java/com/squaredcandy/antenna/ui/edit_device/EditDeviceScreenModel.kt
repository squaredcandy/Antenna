package com.squaredcandy.antenna.ui.edit_device

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.squaredcandy.antenna.domain.device.CoroutineTools
import com.squaredcandy.antenna.domain.device.Device
import com.squaredcandy.antenna.domain.device.DevicePort
import com.squaredcandy.antenna.domain.util.DeviceRepository
import kotlin.random.Random
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditDeviceScreenModel(
    deviceToEdit: Device? = null,
    private val coroutineTools: CoroutineTools,
    private val deviceRepository: DeviceRepository,
) : ScreenModel {

    private val mutableDeviceNameFlow = MutableStateFlow(deviceToEdit?.name)
    private val mutableDeviceMacAddressFlow = MutableStateFlow(deviceToEdit?.macAddress)
    private val mutableDeviceBroadcastIPAddressFlow = MutableStateFlow(deviceToEdit?.broadcastIPAddress)
    private val mutableDevicePortFlow = MutableStateFlow(deviceToEdit?.port)

    private val mutableValidNameStateFlow = MutableStateFlow(true)
    private val mutableValidMACAddressStateFlow = MutableStateFlow(true)
    private val mutableValidBroadcastIPAddressStateFlow = MutableStateFlow(true)

    private val deviceFlow = combine(
        mutableDeviceNameFlow,
        mutableDeviceMacAddressFlow,
        mutableDeviceBroadcastIPAddressFlow,
        mutableDevicePortFlow,
    ) { name,
        macAddress,
        broadcastIPAddress,
        port ->

        Device(
            deviceToEdit?.id ?: Random.nextLong(),
            name ?: "",
            macAddress ?: "",
            broadcastIPAddress ?: "",
            port ?: DevicePort.NINE,
        )
    }

    val viewState by lazy {
        combine(
            deviceFlow,
            mutableValidNameStateFlow,
            mutableValidMACAddressStateFlow,
            mutableValidBroadcastIPAddressStateFlow,
        ) {
            device,
            validName,
            validMACAddress,
            validBroadcastIPAddress ->

            EditDeviceViewState(
                isEditingDevice = deviceToEdit != null,
                device = device,
                validName = validName,
                validMACAddress = validMACAddress,
                validBroadcastIPAddress = validBroadcastIPAddress,
                updateName = ::updateName,
                updateMacAddress = ::updateMacAddress,
                updateBroadcastIPAddress = ::updateBroadcastIPAddress,
                updatePort = ::updatePort,
                onSaveDevice = ::onSaveDevice,
                onBack = ::onBack,
            )
        }
            .stateIn(
                coroutineScope,
                coroutineTools.sharingStarted,
                EditDeviceViewState(
                    isEditingDevice = deviceToEdit != null,
                    device = deviceToEdit ?: Device(),
                    updateName = ::updateName,
                    updateMacAddress = ::updateMacAddress,
                    updateBroadcastIPAddress = ::updateBroadcastIPAddress,
                    updatePort = ::updatePort,
                    onSaveDevice = ::onSaveDevice,
                    onBack = ::onBack,
                )
            )
    }

    private val mutableEvent = MutableSharedFlow<EditDeviceEvent>()
    val event = mutableEvent.asSharedFlow()

    private fun updateName(newName: String) {
        mutableValidNameStateFlow.value = true
        mutableDeviceNameFlow.value = newName
    }

    private fun updateMacAddress(newMacAddress: String) {
        mutableValidMACAddressStateFlow.value = true
        mutableDeviceMacAddressFlow.value = newMacAddress
    }

    private fun updateBroadcastIPAddress(newBroadcastIPAddress: String) {
        mutableValidBroadcastIPAddressStateFlow.value = true
        mutableDeviceBroadcastIPAddressFlow.value = newBroadcastIPAddress
    }

    private fun updatePort(newPort: DevicePort) {
        mutableDevicePortFlow.value = newPort
    }

    private fun onSaveDevice() {
        coroutineScope.launch {
            val validName = validateName()
            if (!validName) {
                mutableValidNameStateFlow.value = false
            }

            val validMACAddress = validateMACAddress()
            if (!validMACAddress) {
                mutableValidMACAddressStateFlow.value = false
            }

            val validBroadcastIPAddress = validateBroadcastIPAddress()
            if (!validBroadcastIPAddress) {
                mutableValidBroadcastIPAddressStateFlow.value = false
            }

            if (!validName || !validMACAddress || !validBroadcastIPAddress) {
                return@launch
            }

            val currentViewState = viewState.value
            if (currentViewState.isEditingDevice) {
                deviceRepository.modifyDevice(currentViewState.device.id, currentViewState.device)
            } else {
                deviceRepository.addDevice(currentViewState.device)
            }
            mutableEvent.emit(EditDeviceEvent.Back)
        }
    }

    private suspend fun validateName(): Boolean = withContext(coroutineTools.default) {
        return@withContext mutableDeviceNameFlow.value?.isNotBlank() ?: false
    }

    private suspend fun validateBroadcastIPAddress(): Boolean = withContext(coroutineTools.default) {
        val ipV4AddressRegex = Regex("^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)(\\.(?!\$)|\$)){4}\$")
        val ipV6AddressRegex = Regex("(?:^|(?<=\\s))(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))(?=\\s|\$)")
        val ipAddress = mutableDeviceBroadcastIPAddressFlow.value ?: return@withContext false
        if (ipAddress.isBlank()) return@withContext false

        return@withContext ipV4AddressRegex.matches(ipAddress) || ipV6AddressRegex.matches(ipAddress)
    }

    private suspend fun validateMACAddress(): Boolean = withContext(coroutineTools.default) {
        val macAddressRegex = Regex("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})\$")
        val macAddress = mutableDeviceMacAddressFlow.value ?: return@withContext false
        return@withContext macAddressRegex.matches(macAddress)
    }

    private fun onBack() {
        coroutineScope.launch {
            mutableEvent.emit(EditDeviceEvent.Back)
        }
    }
}