package net.ezlotest.data.datastore.device

import net.ezlotest.data.api.DeviceApi
import net.ezlotest.domain.entities.DevicesResponse
import net.ezlotest.domain.result.NetworkStatus

class DeviceDataStoreImpl(private val deviceApi: DeviceApi) : DeviceDataStore {

    override suspend fun fetchDevices(): NetworkStatus<DevicesResponse> {
        return try {
            val response = deviceApi.fetchDevices()
            when {
                response.isSuccessful -> NetworkStatus.Success(response.body())
                else -> NetworkStatus.Error(errorMessage = response.message())
            }
        } catch (e: Exception) {
            NetworkStatus.Error(errorMessage = "Unknown error")
        }
    }
}