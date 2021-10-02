package com.project.segunfrancis.lol.repository

import com.project.segunfrancis.lol.api.LolService
import com.project.segunfrancis.lol.api.model.JokeResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LolRepositoryImpl(
    private val service: LolService,
    private val dispatcher: CoroutineDispatcher
) : LolRepository {
    override suspend fun getJokes(category: String): JokeResponse {
        return withContext(dispatcher) {
            service.getJoke(category)
        }
    }
}
