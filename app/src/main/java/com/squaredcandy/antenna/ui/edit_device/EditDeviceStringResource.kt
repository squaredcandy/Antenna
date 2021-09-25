package com.squaredcandy.antenna.ui.edit_device

import android.content.res.Resources
import com.squaredcandy.antenna.R
import com.squaredcandy.antenna.ui.util.StringResource

sealed class EditDeviceStringResource : StringResource {
    data class DeviceTitle(val isEditingDevice: Boolean) : EditDeviceStringResource()
    object DeviceNameLabel : EditDeviceStringResource()
    object MACAddressLabel : EditDeviceStringResource()
    object IPAddressLabel : EditDeviceStringResource()
    object PortLabel : EditDeviceStringResource()
    object UpdateDevice : EditDeviceStringResource()
    object SaveDevice : EditDeviceStringResource()
    object InvalidName : EditDeviceStringResource()
    object InvalidMACAddress : EditDeviceStringResource()
    object InvalidIPAddress : EditDeviceStringResource()

    override fun resolve(resources: Resources): String {
        return when (this) {
            is DeviceTitle -> if (isEditingDevice) {
                resources.getString(R.string.edit_device)
            } else {
                resources.getString(R.string.add_device)
            }
            UpdateDevice -> resources.getString(R.string.update_device)
            SaveDevice -> resources.getString(R.string.save_device)
            DeviceNameLabel -> resources.getString(R.string.device_name_label)
            MACAddressLabel -> resources.getString(R.string.mac_address_label)
            IPAddressLabel -> resources.getString(R.string.ip_address_label)
            PortLabel -> resources.getString(R.string.port_label)
            InvalidName -> resources.getString(R.string.invalid_name)
            InvalidMACAddress -> resources.getString(R.string.invalid_mac_address)
            InvalidIPAddress -> resources.getString(R.string.invalid_ip_address)
        }
    }
}