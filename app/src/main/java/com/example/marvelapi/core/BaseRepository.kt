package com.example.marvelapi.core

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseRepository(
    private val dispatcher: CoroutineDispatcher

) {
    protected suspend fun <T> executeApiCall(call: suspend () -> Response<T>): ApiResult<T> {

        return withContext(dispatcher) {
            val response: Response<T>
            try {
                response = call.invoke()
            } catch (exception: Exception) {
                return@withContext ApiResult.Error(ApiException.IOException(ApiError(errorMessage = exception.message)))
            }
            if (response.isSuccessful) {

                if (response.body() == null) {
                    ApiResult.EmptySuccess
                } else if (response.code() == 204) {
                    ApiResult.EmptySuccess
                } else {
                    ApiResult.Success(response.body()!!)
                }
            } else {
                ApiResult.Error(parseError(response))
            }
        }
    }

    protected fun <T> parseError(response: Response<T>): ApiException {
        val gson = Gson()
        val type = object : TypeToken<ApiError>() {}.type
        val apiError: ApiError? = gson.fromJson(response.errorBody()?.charStream(), type)
        if (apiError == null) {
            return ApiException.IOException(ApiError(errorMessage = "io exception"))
        } else {
            if (response.code() == 401) {
                return ApiException.AuthenticationException(
                    ApiError(
                        errorCode = response.code().toString(), errorMessage = apiError.errorMessage
                    )
                )
            } else {
                return ApiException.IOException(apiError)
            }
        }
    }
}