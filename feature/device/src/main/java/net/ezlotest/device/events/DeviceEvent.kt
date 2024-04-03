package net.ezlotest.device.events

import net.ezlotest.device.DeviceItem
import net.ezlotest.domain.models.Device
import net.ezlotest.ui.SingleEvent

sealed class DeviceEvent : SingleEvent {

    class OnShowDevices(val list: List<DeviceItem>) : SingleEvent
    object OnResetClicked : SingleEvent
    class OnDeviceClicked(val device: Device) : SingleEvent
    class OnEditDeviceClicked(val device: Device) : SingleEvent
    class OnLongDeviceClicked(val pkDevice: String) : SingleEvent

    object OnDeviceUpdated : SingleEvent
    object OnNavigateUp : SingleEvent
}