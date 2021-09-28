package com.project.segunfrancis.lol.di

import com.project.segunfrancis.lol.ui.any.AnyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { AnyViewModel(repository = get()) }
}
