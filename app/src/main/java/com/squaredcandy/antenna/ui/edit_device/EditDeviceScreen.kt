package com.squaredcandy.antenna.ui.edit_device

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.squaredcandy.antenna.domain.device.Device
import com.squaredcandy.antenna.ui.compose.collectAsStateLifecycleAware
import kotlinx.coroutines.flow.SharedFlow
import org.koin.core.parameter.parametersOf

data class EditDeviceScreen(
    val deviceToEdit: Device? = null
) : Screen {
    @Composable
    override fun Content() {
        val editDeviceScreenModel = getScreenModel<EditDeviceScreenModel>(
            parameters = { parametersOf(deviceToEdit) }
        )
        val editDeviceViewState by editDeviceScreenModel.viewState.collectAsStateLifecycleAware()
        editDeviceScreenModel.event.HandleEvent()
        EditDeviceView(editDeviceViewState)
    }

    @Composable
    private fun SharedFlow<EditDeviceEvent>.HandleEvent() {
        val event = collectAsState(initial = null).value ?: return
        when (event) {
            EditDeviceEvent.Back -> {
                val navigator = LocalNavigator.currentOrThrow
                navigator.pop()
            }
        }
    }
}
