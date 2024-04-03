package net.ezlotest.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.ezlotest.database.dao.DeviceDao
import javax.inject.Singleton

const val DATABASE_NAME = "ezlo-database"

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun providesDeviceDatabase(
        @ApplicationContext context: Context,
    ): EzloTestDataBase = Room.databaseBuilder(
        context,
        EzloTestDataBase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    fun providesDeviceDao(database: EzloTestDataBase): DeviceDao = database.deviceDao()
}