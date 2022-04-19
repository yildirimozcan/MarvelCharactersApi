package com.example.marvelapi.di

import com.example.marvelapi.viewmodel.DetailFragmentViewModel
import com.example.marvelapi.viewmodel.repository.DetailFragmentRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule= module {
    factory { DetailFragmentRepository(get(),get()) }
    viewModel { DetailFragmentViewModel(get()) }
}