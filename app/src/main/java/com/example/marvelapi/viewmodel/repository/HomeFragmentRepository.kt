package com.example.marvelapi.viewmodel.repository

import com.example.marvelapi.core.ApiResult
import com.example.marvelapi.core.BaseRepository
import com.example.marvelapi.model.CharacterModel
import com.example.marvelapi.service.CharacterApi
import com.example.marvelapi.utils.MarvelApiConstants.HASH
import com.example.marvelapi.utils.MarvelApiConstants.PUBLIC_KEY
import com.example.marvelapi.utils.MarvelApiConstants.TS
import kotlinx.coroutines.CoroutineDispatcher

class HomeFragmentRepository(
    private val dispatcher: CoroutineDispatcher,
    private val characterApi:CharacterApi
):BaseRepository(dispatcher)
{
    suspend fun getCharactersList():ApiResult<CharacterModel>{
        return executeApiCall {
            characterApi.getCharacters(TS,PUBLIC_KEY,HASH)
        }
    }
}