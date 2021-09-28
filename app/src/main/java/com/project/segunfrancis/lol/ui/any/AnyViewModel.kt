package com.project.segunfrancis.lol.ui.any

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

class AnyViewModel(private val repository: LolRepository) : ViewModel() {

    private val _anyJokesResponse = MutableLiveData<NetworkState<Joke>>()
    val anyJokeResponse: LiveData<NetworkState<Joke>> get() = _anyJokesResponse

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        _anyJokesResponse.postValue(NetworkState.Error(throwable))
    }

    init {
        getAnyJoke(JokeCategory.ANY.value)
    }

    fun getAnyJoke(category: String) {
        _anyJokesResponse.postValue(NetworkState.Loading)
        viewModelScope.launch(coroutineExceptionHandler) {
            val response = repository.getJokes(category)
            Timber.d("${response.mapToJoke()}")
            _anyJokesResponse.postValue(NetworkState.Success(response.mapToJoke()))
        }
    }
}
