package com.example.marvelapi.di

import com.example.marvelapi.viewmodel.HomeFragmentViewModel
import com.example.marvelapi.viewmodel.repository.HomeFragmentRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule= module {
    factory { HomeFragmentRepository(get(),get()) }
    viewModel { HomeFragmentViewModel(get()) }
}