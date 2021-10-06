package com.project.segunfrancis.lol.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.project.segunfrancis.lol.data.LolService
import com.project.segunfrancis.lol.utils.AppConstants.BASE_URL
import com.project.segunfrancis.lol.utils.AppConstants.NETWORK_TIMEOUT
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {

    single<Gson> { GsonBuilder().setLenient().create() }

    single { HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY } }

    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY }
    }

    single {
        OkHttpClient().newBuilder()
            .addInterceptor(provideInterceptor())
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .callTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
            .create(LolService::class.java)
    }
}
