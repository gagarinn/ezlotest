package net.ezlotest.device

import net.ezlotest.domain.entities.Device
import net.ezlotest.domain.entities.Profile

data class DeviceItem(
    val type: DeviceItemType,
    val device: Device? = null,
    val profile: Profile? = null
)
