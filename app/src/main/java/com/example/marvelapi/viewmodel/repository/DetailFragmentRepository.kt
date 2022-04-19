package com.example.marvelapi.viewmodel.repository

import com.example.marvelapi.core.ApiResult
import com.example.marvelapi.core.BaseRepository
import com.example.marvelapi.model.ComicModel
import com.example.marvelapi.service.ComicsApi
import com.example.marvelapi.utils.MarvelApiConstants.HASH
import com.example.marvelapi.utils.MarvelApiConstants.PUBLIC_KEY
import com.example.marvelapi.utils.MarvelApiConstants.TS
import kotlinx.coroutines.CoroutineDispatcher

class DetailFragmentRepository(
    private val dispatcher: CoroutineDispatcher,
    private val comicsApi: ComicsApi
):BaseRepository(dispatcher) {

    suspend fun callComics(characterId:String,dateRange:String,orderBy:String,limit:String):ApiResult<ComicModel>{
        return executeApiCall {
            comicsApi.callComics(characterId,TS,dateRange,orderBy,limit,PUBLIC_KEY,HASH)
        }
    }
}