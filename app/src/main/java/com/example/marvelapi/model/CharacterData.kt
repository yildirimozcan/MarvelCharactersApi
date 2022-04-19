package com.example.marvelapi.model

data class CharacterData(
    val offset:Int?,
    val limit:Int?,
    val total:Int?,
    val count:Int?,
    val results:ArrayList<CharacterResults>?
) {
}