package com.pavellukyanov.themartiancompose.ui.features.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.pavellukyanov.themartiancompose.domain.entity.rovers.Rover
import com.pavellukyanov.themartiancompose.domain.usecases.home.LoadRovers
import com.pavellukyanov.themartiancompose.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val loadRovers: LoadRovers
) : BaseViewModel() {
    private val rovers = savedStateHandle.getStateFlow(ROVERS, emptyList<Rover>())
    private val isLoading = savedStateHandle.getStateFlow(IS_LOADING, true)

    val state = combine(rovers, isLoading) { rovers, isLoading ->
        HomeState(
            isLoading = isLoading,
            rovers = rovers
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeState())

    fun load() = launchIO {
        savedStateHandle[ROVERS] = loadRovers()
        savedStateHandle[IS_LOADING] = false
    }

    companion object {
        private const val ROVERS = "rovers"
        private const val IS_LOADING = "isLoading"
    }
}