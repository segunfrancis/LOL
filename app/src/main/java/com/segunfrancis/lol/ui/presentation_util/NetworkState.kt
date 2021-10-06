package com.segunfrancis.lol.ui.presentation_util

sealed class NetworkState<out T> {
    object Loading : NetworkState<Nothing>()
    data class Success<T>(val joke: T) : NetworkState<T>()
    data class Error(val error: Throwable) : NetworkState<Nothing>()
}
