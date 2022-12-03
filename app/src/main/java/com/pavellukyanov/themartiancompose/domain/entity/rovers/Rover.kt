package com.pavellukyanov.themartiancompose.domain.entity.rovers

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rovers")
data class Rover(
    @PrimaryKey
    val roverName: String,
    val landingDate: String,
    val launchDate: String,
    val status: String,
    val maxDate: String,
    val totalPhotos: Int,
    val type: Rovers?
)
