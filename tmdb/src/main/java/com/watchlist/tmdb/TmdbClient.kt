package com.watchlist.tmdb

import com.watchlist.tmdb.model.Movie
import kotlinx.coroutines.CoroutineDispatcher

interface TmdbClient {

    suspend fun getMovies(): Result<List<Movie>>
}

fun TmdbClient(
    baseUrl: String,
    dispatcher: CoroutineDispatcher,
): TmdbClient {
    val tmdbApi = TmdbApi(baseUrl)
    return TmdbClientImpl(tmdbApi, dispatcher)
}