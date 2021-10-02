package com.project.segunfrancis.lol.di

import com.project.segunfrancis.lol.ui.any.AnyViewModel
import com.project.segunfrancis.lol.ui.christmas.ChristmasViewModel
import com.project.segunfrancis.lol.ui.dark.DarkViewModel
import com.project.segunfrancis.lol.ui.miscellaneous.MiscellaneousViewModel
import com.project.segunfrancis.lol.ui.programming.ProgrammingViewModel
import com.project.segunfrancis.lol.ui.pun.PunViewModel
import com.project.segunfrancis.lol.ui.spooky.SpookyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { AnyViewModel(repository = get()) }
    viewModel { DarkViewModel(repository = get()) }
    viewModel { MiscellaneousViewModel(repository = get()) }
    viewModel { ProgrammingViewModel(repository = get()) }
    viewModel { PunViewModel(repository = get()) }
    viewModel { ChristmasViewModel(repository = get()) }
    viewModel { SpookyViewModel(repository = get()) }
}
