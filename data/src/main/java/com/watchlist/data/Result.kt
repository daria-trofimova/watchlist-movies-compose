package com.watchlist.data

sealed class Result<out E : Any>(open val data: E? = null) {
    class InProgress<E : Any>(
        data: E? = null,
    ) : Result<E>(data)

    class Success<E : Any>(
        override val data: E,
    ) : Result<E>(data)

    class Error<E : Any>(
        data: E? = null,
        val error: Throwable? = null,
    ) : Result<E>(data)
}