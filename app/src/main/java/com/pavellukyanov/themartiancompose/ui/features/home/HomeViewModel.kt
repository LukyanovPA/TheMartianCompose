package com.pavellukyanov.themartiancompose.ui.features.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.pavellukyanov.themartiancompose.domain.entity.rovers.Rover
import com.pavellukyanov.themartiancompose.domain.usecases.home.LoadRovers
import com.pavellukyanov.themartiancompose.domain.usecases.home.UpdateCache
import com.pavellukyanov.themartiancompose.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val loadRovers: LoadRovers,
    private val updateCache: UpdateCache
) : BaseViewModel() {
    private val rovers = savedStateHandle.getStateFlow(ROVERS, emptyList<Rover>())
    private val isLoading = savedStateHandle.getStateFlow(IS_LOADING, true)

    val state = combine(rovers, isLoading) { rovers, isLoading ->
        HomeState(
            isLoading = isLoading,
            rovers = rovers
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(STOP_TIMEOUT), HomeState())

    fun load() = launchIO {
        launchIO { update() }
        loadRovers()
            .onStart { savedStateHandle[IS_LOADING] = true }
            .collect { rovers ->
                savedStateHandle[ROVERS] = rovers
                if (rovers.isNotEmpty()) savedStateHandle[IS_LOADING] = false
            }
    }

    private suspend fun update() {
        updateCache()
    }

    companion object {
        private const val ROVERS = "rovers"
        private const val IS_LOADING = "isLoading"
        private const val STOP_TIMEOUT = 5000L
    }
}