package net.ezlotest.device

import net.ezlotest.domain.models.Device
import net.ezlotest.domain.models.Profile

data class DeviceItem(
    val type: DeviceItemType,
    val device: Device? = null,
    val profile: Profile? = null
)
