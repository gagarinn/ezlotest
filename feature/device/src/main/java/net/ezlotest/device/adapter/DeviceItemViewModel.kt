package net.ezlotest.device.adapter

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import net.ezlotest.device.events.DeviceEvent
import net.ezlotest.domain.models.Device
import net.ezlotest.ui.SingleEvent
import net.ezlotest.ui.utils.DeviceIconResolver.getIcon

class DeviceItemViewModel(
    private val entity: Device?,
    private val callback: ((SingleEvent) -> Unit)?
) {

    private val _title = MutableStateFlow("")
    val title get() = _title.asStateFlow()

    private val _description = MutableStateFlow("")
    val description get() = _description.asStateFlow()

    private val _icon = MutableStateFlow(0)
    val icon get() = _icon.asStateFlow()

    init {
        entity?.let { device ->
            _title.update { device.title }
            _description.update { device.pkDevice }
            _icon.update { device.getIcon() }
        }
    }

    fun onDeviceClick() {
        entity?.let {
            callback?.invoke(DeviceEvent.OnDeviceClicked(it))
        }
    }
}