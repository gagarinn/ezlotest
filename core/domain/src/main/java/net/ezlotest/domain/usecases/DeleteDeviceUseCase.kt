package net.ezlotest.domain.usecases

import net.ezlotest.domain.repositories.DeviceRepository
import javax.inject.Inject

class DeleteDeviceUseCase @Inject constructor(private val repository: DeviceRepository) {

    suspend fun invoke(pkDevice: String) {
        repository.deleteDevice(pkDevice)
    }
}