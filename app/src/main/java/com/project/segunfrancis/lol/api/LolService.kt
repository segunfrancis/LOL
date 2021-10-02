package com.project.segunfrancis.lol.api

import com.project.segunfrancis.lol.api.model.JokeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface LolService {

    @GET("{category}")
    suspend fun getJoke(@Path("category") category: String) : JokeResponse

}