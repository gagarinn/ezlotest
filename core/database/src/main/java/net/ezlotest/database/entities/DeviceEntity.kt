package net.ezlotest.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "devices")
data class DeviceEntity(
    @PrimaryKey
    val pkDevice: String,
    val macAddress: String,
    val pkDeviceType: String,
    val pkDeviceSubType: String,
    val pkAccount: String = "",
    val firmware: String,
    val serverDevice: String,
    val serverEvent: String,
    val serverAccount: String,
    val internalIp: String,
    val lastAliveReported: String,
    val platform: String,
    val title: String
)
