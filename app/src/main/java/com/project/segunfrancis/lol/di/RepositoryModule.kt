package com.project.segunfrancis.lol.di

import com.project.segunfrancis.lol.repository.LolRepository
import com.project.segunfrancis.lol.repository.LolRepositoryImpl
import org.koin.dsl.module

val repoModule = module {
    single<LolRepository> { LolRepositoryImpl(service = get(), dispatcher = get()) }
}
