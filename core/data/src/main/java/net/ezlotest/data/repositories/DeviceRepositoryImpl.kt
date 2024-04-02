package net.ezlotest.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import net.ezlotest.data.datastore.device.DeviceDataStore
import net.ezlotest.domain.entities.Device
import net.ezlotest.domain.mappers.map
import net.ezlotest.domain.repositories.DeviceRepository
import net.ezlotest.domain.result.NetworkStatus
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(private val deviceDataStore: DeviceDataStore) :
    DeviceRepository {

    private val cachedDevices: List<Device> = emptyList()

    override suspend fun fetchDevices(): Flow<NetworkStatus<List<Device>>> {
        val devicesResponse = deviceDataStore.fetchDevices()
        val devices: List<Device> = devicesResponse.data.map()
        return flow {
            emit(NetworkStatus.Loading())
            when {
                devicesResponse is NetworkStatus.Error -> NetworkStatus.Error(devicesResponse.errorMessage)
                devices.isNotEmpty() -> emit(NetworkStatus.Success(devices))
                else -> {
//                    TODO get from DB
                    emit(NetworkStatus.Success(devices))
                }
            }
        }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun getDevices(): Flow<NetworkStatus<List<Device>>> {
        return if (cachedDevices.isNotEmpty()) {
            flow {
                emit(NetworkStatus.Success(cachedDevices))
            }
        } else {
            fetchDevices()
        }
    }
}
