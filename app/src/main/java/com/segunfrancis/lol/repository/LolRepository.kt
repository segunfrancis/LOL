package com.segunfrancis.lol.repository

import com.segunfrancis.lol.data.model.JokeResponse

interface LolRepository {
    suspend fun getJokes(category: String): JokeResponse
}
