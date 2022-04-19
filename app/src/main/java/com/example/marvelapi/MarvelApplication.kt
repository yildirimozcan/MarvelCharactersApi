package com.example.marvelapi

import android.app.Application
import com.example.marvelapi.di.appModule
import com.example.marvelapi.di.detailModule
import com.example.marvelapi.di.homeModule
import com.example.marvelapi.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication:Application() {


    companion object {
        lateinit var instance: MarvelApplication
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MarvelApplication)
            modules(
                listOf(
                    networkModule, homeModule, appModule, detailModule
                )
            )
        }
    }
}