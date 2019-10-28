package com.android.segunfrancis.lol.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {
    companion object {
        private const val BASE_URL = "https://sv443.net/jokeapi/category/"
        var retrofit: Retrofit? = null

        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}