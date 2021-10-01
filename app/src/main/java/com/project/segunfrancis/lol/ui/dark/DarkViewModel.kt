package com.project.segunfrancis.lol.ui.dark

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

class DarkViewModel(private val repository: LolRepository) : ViewModel() {

    private val _darkJokesResponse = MutableLiveData<NetworkState<Joke>>()
    val darkJokeResponse: LiveData<NetworkState<Joke>> get() = _darkJokesResponse

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        _darkJokesResponse.postValue(NetworkState.Error(throwable))
    }

    init {
        getDarkJoke(JokeCategory.DARK.value)
    }

    fun getDarkJoke(category: String) {
        _darkJokesResponse.postValue(NetworkState.Loading)
        viewModelScope.launch(coroutineExceptionHandler) {
            val response = repository.getJokes(category)
            _darkJokesResponse.postValue(NetworkState.Success(response.mapToJoke()))
        }
    }
}
