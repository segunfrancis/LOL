package com.project.segunfrancis.lol.ui.christmas

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

class ChristmasViewModel(private val repository: LolRepository) : ViewModel() {

    private val _christmasJokesResponse = MutableLiveData<NetworkState<Joke>>()
    val christmasJokeResponse: LiveData<NetworkState<Joke>> get() = _christmasJokesResponse

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        _christmasJokesResponse.postValue(NetworkState.Error(throwable))
    }

    init {
        getChristmasJoke(JokeCategory.CHRISTMAS.value)
    }

    fun getChristmasJoke(category: String) {
        _christmasJokesResponse.postValue(NetworkState.Loading)
        viewModelScope.launch(coroutineExceptionHandler) {
            val response = repository.getJokes(category)
            _christmasJokesResponse.postValue(NetworkState.Success(response.mapToJoke()))
        }
    }
}
