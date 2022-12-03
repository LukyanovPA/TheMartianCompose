package com.pavellukyanov.themartiancompose.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pavellukyanov.themartiancompose.data.cache.dao.RoverDao
import com.pavellukyanov.themartiancompose.domain.entity.rovers.Rover

@Database(
    entities = [
        Rover::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MartianDatabase : RoomDatabase() {
    abstract fun rovers(): RoverDao
}