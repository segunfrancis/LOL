package com.segunfrancis.lol.repository

import com.segunfrancis.lol.data.LolService
import com.segunfrancis.lol.data.model.JokeResponse
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
