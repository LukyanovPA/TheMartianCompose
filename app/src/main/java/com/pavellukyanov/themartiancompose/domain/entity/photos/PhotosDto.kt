package com.pavellukyanov.themartiancompose.domain.entity.photos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoDto(
    @SerialName("id") val id: Int,
    @SerialName("sol") val sol: Int,
    @SerialName("camera") val cameraDto: CameraDto,
    @SerialName("img_src") val imgSrc: String,
    @SerialName("earth_date") val earthDate: String,
    @SerialName("rover") val roverDto: RoverDto
)

@Serializable
data class CameraDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("rover_id") val roverId: Int,
    @SerialName("full_name") val fullName: String
)

@Serializable
data class RoverDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("landing_date") val landingDate: String,
    @SerialName("launch_date") val launchDate: String,
    @SerialName("status") val status: String
)

@Serializable
data class LatestDto(
    @SerialName("latest_photos") val photoDtos: List<PhotoDto>
)