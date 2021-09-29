package com.squaredcandy.antenna.ui.edit_device

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.squaredcandy.antenna.domain.device.DevicePort
import com.squaredcandy.antenna.domain.device.resolveString
import com.squaredcandy.antenna.ui.compose.Icon
import com.squaredcandy.antenna.ui.compose.RadioButtonWithLabel
import com.squaredcandy.antenna.ui.compose.Window
import com.squaredcandy.antenna.ui.util.resolve

@Composable
fun EditDeviceView(
    viewState: EditDeviceViewState
) {
    Window {
        Column(
            modifier = Modifier
                .navigationBarsWithImePadding()
                .verticalScroll(rememberScrollState())
        ) {
            TopAppBar(
                modifier = Modifier.semantics { heading() },
                title = {
                    Text(
                        text = EditDeviceStringResource.DeviceTitle(viewState.isEditingDevice)
                            .resolve()
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            viewState.onBack()
                        },
                        content = {
                            Icon(iconResource = EditDeviceIconResource.BackButton)
                        }
                    )
                },
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp,
            )

            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
            ) {
                OutlinedTextField(
                    value = viewState.device.name,
                    onValueChange = viewState.updateName,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = EditDeviceStringResource.DeviceNameLabel.resolve())
                    },
                    isError = !viewState.validName,
                    singleLine = true,
                )

                if (!viewState.validName) {
                    Text(
                        text = EditDeviceStringResource.InvalidName.resolve(),
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.caption,
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
            ) {
                OutlinedTextField(
                    value = viewState.device.macAddress,
                    onValueChange = viewState.updateMacAddress,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = EditDeviceStringResource.MACAddressLabel.resolve())
                    },
                    isError = !viewState.validMACAddress,
                    singleLine = true,
                )

                if (!viewState.validMACAddress) {
                    Text(
                        text = EditDeviceStringResource.InvalidMACAddress.resolve(),
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.caption,
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
            ) {
                OutlinedTextField(
                    value = viewState.device.broadcastIPAddress,
                    onValueChange = viewState.updateBroadcastIPAddress,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = EditDeviceStringResource.BroadcastIPAddressLabel.resolve())
                    },
                    isError = !viewState.validBroadcastIPAddress,
                    singleLine = true,
                )

                if (!viewState.validBroadcastIPAddress) {
                    Text(
                        text = EditDeviceStringResource.InvalidBroadcastIPAddress.resolve(),
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.caption,
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
            ) {
                Text(text = EditDeviceStringResource.PortLabel.resolve())
                Spacer(modifier = Modifier.width(24.dp))
                val devicePorts = DevicePort.values()
                devicePorts.forEachIndexed { index, devicePort ->
                    RadioButtonWithLabel(
                        selected = viewState.device.port == devicePort,
                        onClick = {
                            viewState.updatePort(devicePort)
                        },
                        text = devicePort.resolveString()
                    )
                    if (index != devicePorts.lastIndex) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(60.dp))
        }

        Button(
            modifier = Modifier
                .navigationBarsWithImePadding()
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            onClick = viewState.onSaveDevice,
            content = {
                if (viewState.isEditingDevice) {
                    Text(text = EditDeviceStringResource.UpdateDevice.resolve())
                } else {
                    Text(text = EditDeviceStringResource.SaveDevice.resolve())
                }
            },
        )
    }
}
