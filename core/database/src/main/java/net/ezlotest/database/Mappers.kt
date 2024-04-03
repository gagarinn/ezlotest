package net.ezlotest.database

import net.ezlotest.database.entities.DeviceEntity
import net.ezlotest.domain.models.Device

fun DeviceEntity.toDomain() = Device(
    pkDevice,
    macAddress,
    pkDeviceType,
    pkDeviceSubType,
    pkAccount,
    firmware,
    serverDevice,
    serverEvent,
    serverAccount,
    internalIp,
    lastAliveReported,
    platform,
    title
)

fun Device.toEntity() = DeviceEntity(
    pkDevice,
    macAddress,
    pkDeviceType,
    pkDeviceSubType,
    pkAccount,
    firmware,
    serverDevice,
    serverEvent,
    serverAccount,
    internalIp,
    lastAliveReported,
    platform,
    title
)