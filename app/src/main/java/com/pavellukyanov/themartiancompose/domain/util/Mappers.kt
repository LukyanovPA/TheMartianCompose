package com.pavellukyanov.themartiancompose.domain.util

import com.pavellukyanov.themartiancompose.domain.entity.rovers.Rover
import com.pavellukyanov.themartiancompose.domain.entity.rovers.RoverItemDto
import com.pavellukyanov.themartiancompose.util.DateFormatter

fun RoverItemDto.toRover(): Rover = Rover(
    roverName = name,
    landingDate = DateFormatter.format(landingDate),
    launchDate = DateFormatter.format(launchDate),
    status = status,
    maxDate = DateFormatter.format(maxDate),
    totalPhotos = totalPhotos
)