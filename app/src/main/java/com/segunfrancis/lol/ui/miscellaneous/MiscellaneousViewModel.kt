package com.segunfrancis.lol.ui.miscellaneous

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

class MiscellaneousViewModel(private val repository: LolRepository) : ViewModel() {

    private val _miscJokesResponse = MutableLiveData<NetworkState<Joke>>()
    val miscJokeResponse: LiveData<NetworkState<Joke>> get() = _miscJokesResponse

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
        _miscJokesResponse.postValue(NetworkState.Error(throwable))
    }

    init {
        getMiscJoke(JokeCategory.MISC.value)
    }

    fun getMiscJoke(category: String) {
        _miscJokesResponse.postValue(NetworkState.Loading)
        viewModelScope.launch(coroutineExceptionHandler) {
            val response = repository.getJokes(category)
            _miscJokesResponse.postValue(NetworkState.Success(response.mapToJoke()))
        }
    }
}
