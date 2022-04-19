package com.example.marvelapi.core

sealed class ApiException(val error: ApiError) : Throwable() {
    class AuthenticationException(val apiError: ApiError): ApiException(apiError)
    class IOException(val apiError: ApiError):ApiException(apiError)
}