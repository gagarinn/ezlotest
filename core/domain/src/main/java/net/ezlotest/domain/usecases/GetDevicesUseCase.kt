package net.ezlotest.domain.usecases

import kotlinx.coroutines.flow.Flow
import net.ezlotest.domain.entities.Device
import net.ezlotest.domain.repositories.DeviceRepository
import net.ezlotest.domain.result.NetworkStatus
import javax.inject.Inject

class GetDevicesUseCase @Inject constructor(private val repository: DeviceRepository) {

    suspend fun invoke(): Flow<NetworkStatus<List<Device>>> {
        return repository.getDevices()
    }
}