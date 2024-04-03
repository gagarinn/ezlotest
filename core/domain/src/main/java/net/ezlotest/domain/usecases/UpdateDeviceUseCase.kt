package net.ezlotest.domain.usecases

import net.ezlotest.domain.models.Device
import net.ezlotest.domain.repositories.DeviceRepository
import javax.inject.Inject

class UpdateDeviceUseCase @Inject constructor(private val repository: DeviceRepository) {

    suspend fun invoke(device: Device) {
        repository.updateDevice(device)
    }
}