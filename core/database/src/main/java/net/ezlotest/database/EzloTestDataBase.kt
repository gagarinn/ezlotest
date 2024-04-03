package net.ezlotest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import net.ezlotest.database.dao.DeviceDao
import net.ezlotest.database.entities.DeviceEntity

@Database(
    entities = [
        DeviceEntity::class
    ],
    version = 1
)
abstract class EzloTestDataBase : RoomDatabase() {

    abstract fun deviceDao(): DeviceDao
}
