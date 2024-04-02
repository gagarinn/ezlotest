package net.ezlotest.data.datastore.device

import net.ezlotest.domain.entities.DevicesResponse
import net.ezlotest.domain.result.NetworkStatus

interface DeviceDataStore {

    suspend fun fetchDevices(): NetworkStatus<DevicesResponse>
}