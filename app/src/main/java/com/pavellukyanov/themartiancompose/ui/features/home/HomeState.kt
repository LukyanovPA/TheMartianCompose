package com.pavellukyanov.themartiancompose.ui.features.home

import com.pavellukyanov.themartiancompose.domain.entity.rovers.Rover

data class HomeState(
    val isLoading: Boolean = true,
    val rovers: List<Rover> = emptyList()
)
