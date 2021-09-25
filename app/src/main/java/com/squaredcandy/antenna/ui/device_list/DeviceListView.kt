package com.squaredcandy.antenna.ui.device_list

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.squaredcandy.antenna.domain.device.Device
import com.squaredcandy.antenna.ui.compose.CollectSideEffect
import com.squaredcandy.antenna.ui.compose.Icon
import com.squaredcandy.antenna.ui.compose.Window
import com.squaredcandy.antenna.ui.util.resolve
import com.squaredcandy.antenna.ui.util.resolveDuration
import kotlinx.coroutines.flow.Flow

@Composable
fun DeviceListView(
    viewState: DeviceListViewState,
    sideEffectsFlow: Flow<DeviceListSideEffect>,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val resources = LocalContext.current.resources
    sideEffectsFlow.CollectSideEffect { sideEffect ->
        when (sideEffect) {
            is DeviceListSideEffect.ShowSnackbar -> {
                val message = sideEffect.content.message.resolve(resources)
                val actionLabel = sideEffect.content.actionLabel?.resolve(resources)
                val actionClicked = snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = actionLabel,
                    duration = sideEffect.content.snackbarDuration.resolveDuration()
                )
                when (actionClicked) {
                    SnackbarResult.ActionPerformed -> {
                        sideEffect.content.onActionClicked?.invoke()
                    }
                    else -> {
                    }
                }
            }
        }
    }

    Window {
        if (viewState.deviceList.isNotEmpty()) {
            LazyColumn(
                content = {
                    item {
                        DeviceListTopAppBar(viewState)
                    }

                    items(viewState.deviceList) { item ->
                        DeviceListDeviceItem(viewState, item)
                    }

                    // Add Padding on the bottom for the FAB
                    item {
                        Spacer(modifier = Modifier.height(78.dp))
                    }
                }
            )
        } else {
            DeviceListTopAppBar(viewState)

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = DeviceListStringResource.EmptyContent.resolve(),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewState.onAddDevice() },
                    content = {
                        val contentDescription =
                            DeviceListStringResource.AddWOLDeviceLabel.resolve()
                        Text(
                            text = DeviceListStringResource.AddWOLDevice.resolve(),
                            modifier = Modifier.semantics {
                                this.contentDescription = contentDescription
                            }
                        )
                    }
                )
            }
        }

        Column(
            modifier = Modifier
                .navigationBarsWithImePadding()
                .padding(bottom = 8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            if (viewState.state != DeviceListViewState.State.Empty) {
                FloatingActionButton(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .align(Alignment.End),
                    backgroundColor = MaterialTheme.colors.primary,
                    onClick = viewState.onAddDevice,
                    content = {
                        Icon(iconResource = DeviceListIconResource.AddNewDevice)
                    },
                )
            }
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { data ->
                    Snackbar(
                        snackbarData = data,
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DeviceListDeviceItem(viewState: DeviceListViewState, item: Device) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .let {
                return@let when (viewState.state) {
                    DeviceListViewState.State.Populated -> {
                        it.combinedClickable(
                            onClick = {
                                viewState.onWakeDevice(item)
                            },
                            onClickLabel = DeviceListStringResource.WakeDeviceLabel.resolve(),
                            role = Role.Button,
                            onLongClick = {
                                viewState.toggleSelected(item.id)
                            },
                            onLongClickLabel = DeviceListStringResource.ToggleSelectedLabel.resolve(),
                        )
                    }
                    DeviceListViewState.State.Editing -> {
                        it.clickable(
                            role = Role.Button,
                            onClick = {
                                viewState.toggleSelected(item.id)
                            },
                            onClickLabel = DeviceListStringResource.ToggleSelectedLabel.resolve()
                        )
                    }
                    else -> {
                        it
                    }
                }
            }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (viewState.state == DeviceListViewState.State.Editing) {

                Checkbox(
                    // Semantics handled by parent
                    modifier = Modifier.clearAndSetSemantics {},
                    checked = viewState.selectedDeviceList.contains(item.id),
                    onCheckedChange = {
                        viewState.toggleSelected(item.id)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colors.primary.copy(alpha = ContentAlpha.high)
                    ),
                )

                Spacer(modifier = Modifier.width(16.dp))
            }

            Column(
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.body1,
                )
                Text(
                    text = item.macAddress,
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DeviceListTopAppBar(viewState: DeviceListViewState) {
    TopAppBar(
        title = {
            Text(
                text = DeviceListStringResource.Title.resolve(),
                modifier = Modifier.semantics { heading() }
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        navigationIcon = if (viewState.state == DeviceListViewState.State.Editing) {
            {
                IconButton(
                    onClick = {
                        viewState.clearSelected()
                    },
                    content = {
                        Icon(iconResource = DeviceListIconResource.StopEditing)
                    },
                )
            }
        } else null,
        actions = {
            if (viewState.state != DeviceListViewState.State.Editing) return@TopAppBar
            if (viewState.selectedDeviceList.size == 1) {
                IconButton(
                    onClick = {
                        viewState.editSelected()
                    },
                    content = {
                        Icon(iconResource = DeviceListIconResource.EditSelectedDevice)
                    },
                )
            }

            IconButton(
                onClick = {
                    viewState.deleteAllSelected()
                },
                content = {
                    Icon(iconResource = DeviceListIconResource.DeleteAllSelectedDevices)
                }
            )
        },
    )
}
