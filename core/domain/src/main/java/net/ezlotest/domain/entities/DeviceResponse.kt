package net.ezlotest.domain.entities

data class DeviceResponse(
    val PK_Device: String,
    val MacAddress: String,
    val PK_DeviceType: String,
    val PK_Account: String?,
    val PK_DeviceSubType: String,
    val Firmware: String,
    val Server_Device: String,
    val Server_Event: String,
    val Server_Account: String,
    val InternalIP: String,
    val LastAliveReported: String,
    val Platform: String,
)
