package net.ezlotest.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import net.ezlotest.database.entities.DeviceEntity

@Dao
interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevices(devices: List<DeviceEntity>)

    @Update
    suspend fun update(entity: DeviceEntity)

    @Query("SELECT * FROM devices")
    suspend fun getDevices(): List<DeviceEntity>

    @Query("DELETE FROM devices WHERE pkDevice = :pkDevice")
    suspend fun deleteDevice(pkDevice: String)
}