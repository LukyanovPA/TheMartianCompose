package com.pavellukyanov.themartiancompose.domain.usecases.home

import javax.inject.Inject

class UpdateCache @Inject constructor(
    private val repo: RoverRepository
) {
    suspend operator fun invoke() = repo.updateCache()
}