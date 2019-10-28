package com.android.segunfrancis.lol.api

import com.android.segunfrancis.lol.data.JokeResponse
import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET(value = "any")
    suspend fun getAnyJoke(): Call<JokeResponse>

    @GET(value = "programming")
    suspend fun getProgrammingJoke(): Call<JokeResponse>

    @GET(value = "dark")
    suspend fun getDarkJoke(): Call<JokeResponse>

    @GET(value = "miscellaneous")
    suspend fun getMiscellaneousJoke(): Call<JokeResponse>
}