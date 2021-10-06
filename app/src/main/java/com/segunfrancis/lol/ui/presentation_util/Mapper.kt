package com.segunfrancis.lol.ui.presentation_util

import com.segunfrancis.lol.data.model.JokeResponse
import com.segunfrancis.lol.ui.model.Joke
import com.segunfrancis.lol.utils.AppConstants.SINGLE_JOKE_TYPE
import com.segunfrancis.lol.utils.AppConstants.TWO_JOKE_TYPE

fun JokeResponse.mapToJoke(): Joke {
    return when (type) {
        SINGLE_JOKE_TYPE -> Joke.OneTypeJoke(
            id = id,
            category = category,
            joke = joke
        )
        TWO_JOKE_TYPE -> Joke.TwoTypeJoke(
            id = id,
            category = category,
            setup = setup,
            delivery = delivery
        )
        else -> throw IllegalArgumentException("Unknown joke type")
    }
}
