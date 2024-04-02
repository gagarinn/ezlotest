package net.ezlotest.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.ezlotest.data.api.DeviceApi
import net.ezlotest.data.datastore.device.DeviceDataStore
import net.ezlotest.data.datastore.device.DeviceDataStoreImpl

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideDeviceDataSource(api: DeviceApi): DeviceDataStore {
        return DeviceDataStoreImpl(api)
    }
}