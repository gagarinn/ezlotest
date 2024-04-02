package net.ezlotest.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Device(
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
) : Parcelable
