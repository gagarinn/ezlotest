package net.ezlotest.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.ezlotest.data.api.DeviceApi
import net.ezlotest.data.datastore.device.DatabaseDeviceDataStore
import net.ezlotest.data.datastore.device.DatabaseDeviceDataStoreImpl
import net.ezlotest.data.datastore.device.DeviceDataStore
import net.ezlotest.data.datastore.device.DeviceDataStoreImpl
import net.ezlotest.database.dao.DeviceDao

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideDeviceDataSource(api: DeviceApi): DeviceDataStore {
        return DeviceDataStoreImpl(api)
    }

    @Provides
    fun provideDbDeviceDataSource(deviceDao: DeviceDao): DatabaseDeviceDataStore {
        return DatabaseDeviceDataStoreImpl(deviceDao)
    }
}