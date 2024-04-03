package net.ezlotest.data.datastore.device

import net.ezlotest.database.entities.DeviceEntity

interface DatabaseDeviceDataStore {

    suspend fun saveDevices(devices: List<DeviceEntity>)

    suspend fun update(entity: DeviceEntity)

    suspend fun getDevices(): List<DeviceEntity>

    suspend fun deleteDevice(pkDevice: String)
}