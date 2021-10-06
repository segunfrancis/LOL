package com.segunfrancis.lol.di

import com.segunfrancis.lol.repository.LolRepository
import com.segunfrancis.lol.repository.LolRepositoryImpl
import org.koin.dsl.module

val repoModule = module {
    single<LolRepository> { LolRepositoryImpl(service = get(), dispatcher = get()) }
}
