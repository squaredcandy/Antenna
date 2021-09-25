package com.squaredcandy.antenna.ui.edit_device

import android.content.res.Resources
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector
import com.squaredcandy.antenna.R
import com.squaredcandy.antenna.ui.compose.IconResource

sealed class EditDeviceIconResource : IconResource {
    object BackButton : EditDeviceIconResource()

    override fun resolveIcon(resources: Resources): ImageVector {
        return when (this) {
            BackButton -> Icons.Filled.ArrowBack
        }
    }

    override fun resolveContentDescription(resources: Resources): String? {
        return when (this) {
            BackButton -> resources.getString(R.string.go_back)
        }
    }
}