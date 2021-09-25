package com.squaredcandy.antenna.ui.device_list

import android.content.res.Resources
import com.squaredcandy.antenna.R
import com.squaredcandy.antenna.ui.util.StringResource

sealed class DeviceListStringResource : StringResource {
    object Title : DeviceListStringResource()
    object EmptyContent : DeviceListStringResource()
    object AddWOLDevice : DeviceListStringResource()
    object AddWOLDeviceLabel : DeviceListStringResource()
    object SignalSent : DeviceListStringResource()
    object FailedToSend : DeviceListStringResource()
    object TryAgain : DeviceListStringResource()
    object WakeDeviceLabel : DeviceListStringResource()
    object ToggleSelectedLabel : DeviceListStringResource()

    override fun resolve(resources: Resources): String {
        return when (this) {
            Title -> resources.getString(R.string.title)
            SignalSent -> resources.getString(R.string.signal_sent)
            FailedToSend -> resources.getString(R.string.failed_to_send)
            TryAgain -> resources.getString(R.string.try_again)
            EmptyContent -> resources.getString(R.string.empty_content)
            AddWOLDevice -> resources.getString(R.string.add_wol_device)
            AddWOLDeviceLabel -> resources.getString(R.string.add_wol_device_label)
            WakeDeviceLabel -> resources.getString(R.string.wake_device_label)
            ToggleSelectedLabel -> resources.getString(R.string.toggle_selected_label)
        }
    }
}