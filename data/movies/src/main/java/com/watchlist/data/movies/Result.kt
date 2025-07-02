package com.watchlist.data.movies

public sealed class Result<out T> {
    public data class InProgress<out T>(val data: T? = null) : Result<T>()
    public data class Success<out T>(val data: T) : Result<T>()
    public data class Error<out T>(val error: Throwable? = null, val data: T? = null) : Result<T>()
}