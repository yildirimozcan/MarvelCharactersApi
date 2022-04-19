package com.example.marvelapi.model

data class ComicResults(
    val id:Int?,
    val digitalId:Int?,
    val title:String?,
    val dates:ArrayList<ComicDates>?
)
