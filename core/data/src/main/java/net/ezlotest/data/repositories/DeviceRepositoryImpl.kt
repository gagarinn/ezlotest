package net.ezlotest.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import net.ezlotest.data.datastore.device.DatabaseDeviceDataStore
import net.ezlotest.data.datastore.device.DeviceDataStore
import net.ezlotest.database.toDomain
import net.ezlotest.database.toEntity
import net.ezlotest.domain.models.Device
import net.ezlotest.domain.mappers.map
import net.ezlotest.domain.repositories.DeviceRepository
import net.ezlotest.domain.result.NetworkStatus
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    private val deviceDataStore: DeviceDataStore,
    private val databaseDeviceDataStore: DatabaseDeviceDataStore
) : DeviceRepository {

    override suspend fun fetchDevices(): Flow<NetworkStatus<List<Device>>> {
        val devicesResponse = deviceDataStore.fetchDevices()
        val devices: List<Device> = devicesResponse.data.map().sortedBy { it.pkDevice }
        return flow {
            emit(NetworkStatus.Loading())
            when {
                devicesResponse is NetworkStatus.Error -> NetworkStatus.Error(devicesResponse.errorMessage)
                devices.isNotEmpty() -> {
                    databaseDeviceDataStore.saveDevices(devices.map { it.toEntity() })
                    emit(NetworkStatus.Success(devices))
                }
            }
        }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun getDevices(): Flow<NetworkStatus<List<Device>>> {
        val dBDevices = databaseDeviceDataStore.getDevices()
        return if (dBDevices.isNotEmpty()) {
            flow {
                emit(NetworkStatus.Success(dBDevices.map { it.toDomain() }))
            }
        } else {
            fetchDevices()
        }
    }

    override suspend fun deleteDevice(pkDevice: String) {
        databaseDeviceDataStore.deleteDevice(pkDevice)
    }

    override suspend fun updateDevice(device: Device) {
        databaseDeviceDataStore.update(device.toEntity())
    }
}
