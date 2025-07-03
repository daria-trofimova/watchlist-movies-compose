package com.watchlist.data.movies

public sealed class Result<out T> {
    public data class InProgress<out T>(val data: T? = null) : Result<T>()
    public data class Success<out T>(val data: T) : Result<T>()
    public data class Error<out T>(val error: Throwable? = null, val data: T? = null) : Result<T>()
}

public fun <T, R> Result<T>.mapData(transform: (T) -> R): Result<R> = when (this) {
    is Result.InProgress -> Result.InProgress(data?.let(transform))
    is Result.Error -> Result.Error(error, data?.let(transform))
    is Result.Success -> Result.Success(transform(data))
}