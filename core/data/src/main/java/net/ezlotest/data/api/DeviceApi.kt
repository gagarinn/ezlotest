package net.ezlotest.data.api

import net.ezlotest.domain.entities.DevicesResponse
import retrofit2.Response
import retrofit2.http.GET

interface DeviceApi {

    @GET("test_android/items.test")
    suspend fun fetchDevices(): Response<DevicesResponse>
}