package com.pavellukyanov.themartiancompose.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {
    protected fun launchUI(action: suspend CoroutineScope.() -> Unit) = launch(Dispatchers.Main, action)
    protected fun launchIO(action: suspend CoroutineScope.() -> Unit) = launch(Dispatchers.IO, action)
    protected fun launchCPU(action: suspend CoroutineScope.() -> Unit) = launch(Dispatchers.Default, action)

    private fun launch(dispatcher: CoroutineDispatcher, action: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(dispatcher) {
            try {
                action()
            } catch (throwable: Throwable) {
                Timber.e(throwable)
            }
        }
    }
}