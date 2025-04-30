package com.watchlist.data.movies

sealed class Result<out T> {
    data class InProgress<out T>(val data: T? = null) : Result<T>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(val error: Throwable? = null, val data: T? = null) : Result<T>()
}