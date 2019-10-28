package com.android.segunfrancis.lol.api

import androidx.lifecycle.MutableLiveData
import com.android.segunfrancis.lol.data.JokeResponse
import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET(value = "any")
    fun getAnyJoke(): Call<JokeResponse>

    @GET(value = "programming")
    fun getProgrammingJoke(): Call<MutableLiveData<JokeResponse>>

    @GET(value = "dark")
    fun getDarkJoke(): Call<MutableLiveData<JokeResponse>>

    @GET(value = "miscellaneous")
    fun getMiscellaneousJoke(): Call<MutableLiveData<JokeResponse>>
}