package com.android.segunfrancis.lol.api

import com.android.segunfrancis.lol.data.JokeResponse
import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET(value = "any")
    fun getAnyJoke(): Call<JokeResponse>

    @GET(value = "programming")
    fun getProgrammingJoke(): Call<JokeResponse>

    @GET(value = "dark")
    fun getDarkJoke(): Call<JokeResponse>

    @GET(value = "miscellaneous")
    fun getMiscellaneousJoke(): Call<JokeResponse>
}