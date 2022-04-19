package com.example.marvelapi.service

import com.example.marvelapi.model.ComicModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicsApi {

    @GET("/v1/public/characters/{id}/comics")
    suspend fun callComics(@Path("id") id:String,
                           @Query("ts") ts:String,
                           @Query("dateRange") dateRange:String,
                           @Query("orderBy") orderBy:String,
                           @Query("limit") limit:String,
                           @Query("apikey") apikey:String,
                           @Query("hash") hash:String):Response<ComicModel>

}