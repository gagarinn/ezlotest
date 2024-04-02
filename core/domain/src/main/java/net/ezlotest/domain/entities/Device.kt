package net.ezlotest.domain.entities

data class Device(
    val pkDevice: String,
    val macAddress: String,
    val pkDeviceType: String,
    val pkDeviceSubType: String,
    val firmware: String,
    val serverDevice: String,
    val serverEvent: String,
    val serverAccount: String,
    val internalIp: String,
    val lastAliveReported: String,
    val platform: String,
)
