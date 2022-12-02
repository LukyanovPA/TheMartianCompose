package com.pavellukyanov.themartiancompose.domain.entity.rovers

data class Rover(
    val roverName: String,
    val landingDate: String,
    val launchDate: String,
    val status: String,
    val maxDate: String,
    val totalPhotos: Int
)
