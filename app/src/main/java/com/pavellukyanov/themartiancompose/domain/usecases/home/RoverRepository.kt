package com.pavellukyanov.themartiancompose.domain.usecases.home

import com.pavellukyanov.themartiancompose.domain.entity.rovers.Rover

interface RoverRepository {
    suspend fun load(): List<Rover>
}