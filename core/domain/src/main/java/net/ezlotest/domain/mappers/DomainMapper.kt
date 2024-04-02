package net.ezlotest.domain.mappers

import net.ezlotest.domain.entities.Device
import net.ezlotest.domain.entities.DeviceResponse
import net.ezlotest.domain.entities.DevicesResponse

fun DevicesResponse?.map(): List<Device> {
    this?.let { devicesResponse ->
        return devicesResponse.Devices.mapIndexed { index, device -> device.toDevice(index) }
    }
    return emptyList()
}

private fun DeviceResponse.toDevice(index: Int) = Device(
    pkDevice = PK_Device,
    macAddress = MacAddress,
    pkDeviceType = PK_DeviceType,
    pkDeviceSubType = PK_DeviceSubType,
    pkAccount = PK_Account.orEmpty(),
    firmware = Firmware,
    serverDevice = Server_Device,
    serverEvent = Server_Event,
    serverAccount = Server_Account,
    internalIp = InternalIP,
    lastAliveReported = LastAliveReported,
    platform = Platform,
    title = "Home Number ${index + 1}"
)