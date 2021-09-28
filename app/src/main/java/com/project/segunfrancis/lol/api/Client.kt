package com.project.segunfrancis.lol.api

import com.project.segunfrancis.lol.utils.AppConstants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Deprecated("Replace with DI implementation")
class Client {
    companion object {
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