package com.segunfrancis.lol.ui.model

sealed class Joke {
    data class OneTypeJoke(val id: Int, val category: String, val joke: String) : Joke()
    data class TwoTypeJoke(val id: Int, val category: String, val setup: String, val delivery: String) : Joke()
}
