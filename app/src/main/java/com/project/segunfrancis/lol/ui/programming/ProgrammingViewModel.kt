package com.project.segunfrancis.lol.ui.programming

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

class ProgrammingViewModel(private val repository: LolRepository) : ViewModel() {

    private val _programmingJokesResponse = MutableLiveData<NetworkState<Joke>>()
    val programmingJokeResponse: LiveData<NetworkState<Joke>> get() = _programmingJokesResponse

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        _programmingJokesResponse.postValue(NetworkState.Error(throwable))
    }

    init {
        getAnyJoke(JokeCategory.PROGRAMMING.value)
    }

    fun getAnyJoke(category: String) {
        _programmingJokesResponse.postValue(NetworkState.Loading)
        viewModelScope.launch(coroutineExceptionHandler) {
            val response = repository.getJokes(category)
            _programmingJokesResponse.postValue(NetworkState.Success(response.mapToJoke()))
        }
    }
}
