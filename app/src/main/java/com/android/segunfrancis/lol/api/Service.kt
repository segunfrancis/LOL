package com.android.segunfrancis.lol.api

import com.android.segunfrancis.lol.data.AlternateJokeResponse
import com.android.segunfrancis.lol.data.JokeResponse
import com.android.segunfrancis.lol.data.MiscellaneousJokeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface Service {
    @GET(value = "any")
    fun getAnyJoke(): Call<JokeResponse>

    @GET
    fun getAlternateJoke(@Url url: String): Call<AlternateJokeResponse>

    @GET(value = "programming")
    fun getProgrammingJoke(): Call<JokeResponse>

    @GET(value = "dark")
    fun getDarkJoke(): Call<JokeResponse>

    @GET(value = "miscellaneous")
    fun getMiscellaneousJoke(): Call<JokeResponse>

    @GET
    @Headers(value = ["Accept: application/json"])
    fun getMiscellaneousJoke2(@Url url: String): Call<MiscellaneousJokeResponse>
}