package com.pavellukyanov.themartiancompose.domain.usecases.home

import com.pavellukyanov.themartiancompose.domain.entity.rovers.Rover
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadRovers @Inject constructor(
    private val repo: RoverRepository
) {
    suspend operator fun invoke(): Flow<List<Rover>> = repo.load()
}