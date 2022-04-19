package com.example.marvelapi.core

data class ApiError(
    val errorCode: String? = null,
    val errorMessage: String? = null,
    val errorType: String? = null
)