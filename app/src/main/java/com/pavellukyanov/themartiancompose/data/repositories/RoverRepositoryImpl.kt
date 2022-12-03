package com.pavellukyanov.themartiancompose.data.repositories

import com.pavellukyanov.themartiancompose.data.api.services.RoverService
import com.pavellukyanov.themartiancompose.data.api.util.toData
import com.pavellukyanov.themartiancompose.data.cache.MartianDatabase
import com.pavellukyanov.themartiancompose.domain.entity.rovers.Rover
import com.pavellukyanov.themartiancompose.domain.entity.rovers.Rovers
import com.pavellukyanov.themartiancompose.domain.usecases.home.RoverRepository
import com.pavellukyanov.themartiancompose.domain.util.toRover
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoverRepositoryImpl @Inject constructor(
    private val roverApi: RoverService,
    private val cache: MartianDatabase
) : RoverRepository {

    override suspend fun load(): Flow<List<Rover>> = cache.rovers().loadAll()

    override suspend fun updateCache() {
        cache.rovers().insert(
            getRoverNames().map { getRoverFromNetwork(it) }
        )
    }

    private fun getRoverFromNetwork(roverName: String): Rover =
        roverApi.loadRoverInfo(roverName).toData().roverItem.toRover()

    private fun getRoverNames(): List<String> = Rovers.values().map { it.roverName }
}