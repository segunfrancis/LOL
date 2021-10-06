package com.segunfrancis.lol.ui.spooky

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.segunfrancis.lol.repository.LolRepository
import com.segunfrancis.lol.ui.model.Joke
import com.segunfrancis.lol.ui.model.JokeCategory
import com.segunfrancis.lol.ui.presentation_util.NetworkState
import com.segunfrancis.lol.ui.presentation_util.mapToJoke
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber

class SpookyViewModel(private val repository: LolRepository) : ViewModel() {

    private val _spookyJokesResponse = MutableLiveData<NetworkState<Joke>>()
    val spookyJokeResponse: LiveData<NetworkState<Joke>> get() = _spookyJokesResponse

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        _spookyJokesResponse.postValue(NetworkState.Error(throwable))
    }

    init {
        getSpookyJoke(JokeCategory.SPOOKY.value)
    }

    fun getSpookyJoke(category: String) {
        _spookyJokesResponse.postValue(NetworkState.Loading)
        viewModelScope.launch(coroutineExceptionHandler) {
            val response = repository.getJokes(category)
            _spookyJokesResponse.postValue(NetworkState.Success(response.mapToJoke()))
        }
    }
}
