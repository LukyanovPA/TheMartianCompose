package com.pavellukyanov.themartiancompose.domain.usecases.home

import com.pavellukyanov.themartiancompose.domain.entity.rovers.Rover
import kotlinx.coroutines.flow.Flow

interface RoverRepository {
    suspend fun load(): Flow<List<Rover>>
    suspend fun updateCache()
}