package net.ezlotest.device.events

import net.ezlotest.device.DeviceItem
import net.ezlotest.domain.models.Device
import net.ezlotest.ui.SingleEvent

sealed class DeviceEvent : SingleEvent {

    class OnShowDevices(val list: List<DeviceItem>) : SingleEvent

    class OnDeviceClicked(val device: Device) : SingleEvent
}