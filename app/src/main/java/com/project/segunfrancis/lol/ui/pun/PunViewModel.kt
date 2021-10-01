package com.project.segunfrancis.lol.ui.pun

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.segunfrancis.lol.repository.LolRepository
import com.project.segunfrancis.lol.ui.model.Joke
import com.project.segunfrancis.lol.ui.model.JokeCategory
import com.project.segunfrancis.lol.ui.presentation_util.NetworkState
import com.project.segunfrancis.lol.ui.presentation_util.mapToJoke
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber

class PunViewModel(private val repository: LolRepository) : ViewModel() {

    private val _punJokesResponse = MutableLiveData<NetworkState<Joke>>()
    val punJokeResponse: LiveData<NetworkState<Joke>> get() = _punJokesResponse

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        _punJokesResponse.postValue(NetworkState.Error(throwable))
    }

    init {
        getPunJoke(JokeCategory.PUN.value)
    }

    fun getPunJoke(category: String) {
        _punJokesResponse.postValue(NetworkState.Loading)
        viewModelScope.launch(coroutineExceptionHandler) {
            val response = repository.getJokes(category)
            _punJokesResponse.postValue(NetworkState.Success(response.mapToJoke()))
        }
    }
}
