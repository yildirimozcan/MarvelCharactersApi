package com.example.marvelapi.service

import com.example.marvelapi.model.CharacterModel
import com.example.marvelapi.utils.MarvelApiConstants.HASH
import com.example.marvelapi.utils.MarvelApiConstants.PUBLIC_KEY
import com.example.marvelapi.utils.MarvelApiConstants.TS
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

   @GET("/v1/public/characters")
    suspend fun getCharacters(@Query("ts") ts:String,
                              @Query("apikey") apikey:String,
                              @Query("hash") hash:String):Response<CharacterModel>



   /* @GET("v1/public/characters?ts=1&apikey=193b8f25fc60bcc9655bd9780a0098a5&hash=8940220eb2e0716037bb06fd07599ef1")
    suspend fun getCharacters():Response<CharacterModel>

    */

}