package com.example.marvelapi.core

sealed class ApiResult<out T> {
    class Success<T>(val value: T) : ApiResult<T>()

    object EmptySuccess : ApiResult<Nothing>()

    class Error<T>(val apiError: ApiException) : ApiResult<T>()

    fun onSuccess(successHandler: (T) -> Unit): ApiResult<T> = this.also {
        when (this) {
            is Success -> successHandler(value)
        }
    }

    fun onEmptySuccess(successHandler: () -> Unit): ApiResult<T> = this.also {
        when (this) {
            is EmptySuccess -> successHandler()
        }
    }

    fun onApiError(errorHandler: (ApiException) -> Unit): ApiResult<T> = this.also {
        when (this) {
            is Error -> errorHandler(apiError)
        }
    }
}