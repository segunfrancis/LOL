package com.segunfrancis.lol.data.model

import androidx.annotation.Keep

@Keep
data class JokeResponse(
    val category: String,
    val delivery: String,
    val error: Boolean,
    val id: Int,
    val lang: String,
    val safe: Boolean,
    val setup: String,
    val type: String,
    val joke: String
)
