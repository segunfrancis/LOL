package com.project.segunfrancis.lol.repository

import com.project.segunfrancis.lol.data.model.JokeResponse

interface LolRepository {
    suspend fun getJokes(category: String): JokeResponse
}
