package com.project.segunfrancis.lol.data

import com.project.segunfrancis.lol.data.model.JokeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LolService {

    @GET("{category}/{blacklistFlags}/{lang}")
    suspend fun getJoke(
        @Path("category") category: String,
        @Query("lang") language: String = "en",
        @Query("blacklistFlags") blackList: String = "religious"
    ): JokeResponse
}
