package com.example.marvelapi.di

import com.example.marvelapi.BuildConfig
import com.example.marvelapi.service.CharacterApi
import com.example.marvelapi.service.ComicsApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule= module {
    single { createOkHttpClient()}
    single { createRetrofit(okHttpClient = get()) }
    factory { createWebService<CharacterApi>(get()) }
    factory { createWebService<ComicsApi>(get()) }
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

inline fun <reified T> createWebService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}
