package com.project.segunfrancis.lol.repository

import com.project.segunfrancis.lol.api.model.JokeResponse

interface LolRepository {
    suspend fun getJokes(category: String): JokeResponse
}