package net.ezlotest.domain.repositories

import kotlinx.coroutines.flow.Flow
import net.ezlotest.domain.models.Device
import net.ezlotest.domain.result.NetworkStatus

interface DeviceRepository {

    suspend fun fetchDevices(): Flow<NetworkStatus<List<Device>>>

    suspend fun getDevices(): Flow<NetworkStatus<List<Device>>>

    suspend fun deleteDevice(pkDevice: String)

    suspend fun updateDevice(device: Device)
}