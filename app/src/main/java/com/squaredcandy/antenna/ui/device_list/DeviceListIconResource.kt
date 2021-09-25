package com.squaredcandy.antenna.ui.device_list

import android.content.res.Resources
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.graphics.vector.ImageVector
import com.squaredcandy.antenna.R
import com.squaredcandy.antenna.ui.compose.IconResource

sealed class DeviceListIconResource : IconResource {
    object AddNewDevice : DeviceListIconResource()
    object StopEditing : DeviceListIconResource()
    object EditSelectedDevice : DeviceListIconResource()
    object DeleteAllSelectedDevices : DeviceListIconResource()

    override fun resolveIcon(resources: Resources): ImageVector {
        return when (this) {
            AddNewDevice -> Icons.Filled.Add
            StopEditing -> Icons.Filled.Close
            EditSelectedDevice -> Icons.Filled.Edit
            DeleteAllSelectedDevices -> Icons.Filled.Delete
        }
    }

    override fun resolveContentDescription(resources: Resources): String? {
        return when (this) {
            AddNewDevice -> resources.getString(R.string.add_new_device)
            StopEditing -> resources.getString(R.string.stop_editing)
            EditSelectedDevice -> resources.getString(R.string.edit_selected_device)
            DeleteAllSelectedDevices -> resources.getString(R.string.delete_all_selected_devices)
        }
    }
}