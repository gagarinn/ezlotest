package net.ezlotest.data.datastore.device

import net.ezlotest.database.dao.DeviceDao
import net.ezlotest.database.entities.DeviceEntity

class DatabaseDeviceDataStoreImpl(private val deviceDao: DeviceDao) : DatabaseDeviceDataStore {

    override suspend fun saveDevices(devices: List<DeviceEntity>) {
        deviceDao.saveDevices(devices)
    }

    override suspend fun update(entity: DeviceEntity) {
        deviceDao.update(entity)
    }

    override suspend fun getDevices(): List<DeviceEntity> = deviceDao.getDevices()

    override suspend fun deleteDevice(pkDevice: String) {
        deviceDao.deleteDevice(pkDevice)
    }
}