package com.pavellukyanov.themartiancompose.data.cache

import androidx.room.TypeConverter
import com.pavellukyanov.themartiancompose.domain.entity.rovers.Rovers

class Converters {
    @TypeConverter
    fun toRoverTypes(value: String) = enumValueOf<Rovers>(value)

    @TypeConverter
    fun fromRoverTypes(value: Rovers) = value.name
}