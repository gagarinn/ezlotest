package net.ezlotest.domain.mappers

import net.ezlotest.domain.entities.Device
import net.ezlotest.domain.entities.DeviceResponse
import net.ezlotest.domain.entities.DevicesResponse

fun DevicesResponse?.map(): List<Device> {
    this?.let { devicesResponse ->
        return devicesResponse.Devices.map { it.toDevice() }
    }
    return emptyList()
}

private fun DeviceResponse.toDevice() = Device(
    pkDevice = PK_Device,
    macAddress = MacAddress,
    pkDeviceType = PK_DeviceType,
    pkDeviceSubType = PK_DeviceSubType,
    firmware = Firmware,
    serverDevice = Server_Device,
    serverEvent = Server_Event,
    serverAccount = Server_Account,
    internalIp = InternalIP,
    lastAliveReported = LastAliveReported,
    platform = Platform
)
