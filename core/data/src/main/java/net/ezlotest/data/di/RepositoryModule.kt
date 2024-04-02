package net.ezlotest.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.ezlotest.data.repositories.DeviceRepositoryImpl
import net.ezlotest.domain.repositories.DeviceRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindDeviceRepository(repositoryImpl: DeviceRepositoryImpl): DeviceRepository
}