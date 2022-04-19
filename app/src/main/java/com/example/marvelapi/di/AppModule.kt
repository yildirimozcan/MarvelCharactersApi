package com.example.marvelapi.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val appModule= module {
    factory { provideIODispatcher() }
}

private fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO