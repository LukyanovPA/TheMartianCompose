package com.pavellukyanov.themartiancompose.data.api.util

import com.pavellukyanov.themartiancompose.util.ServerException
import retrofit2.Call

fun <T> Call<T>.toData(): T {
    val response = execute()
    if (response.isSuccessful) {
        return response.body()!!
    } else {
        throw ServerException(response.code(), response.errorBody()?.string())
    }
}