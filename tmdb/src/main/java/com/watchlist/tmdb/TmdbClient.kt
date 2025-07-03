package com.watchlist.tmdb

import com.watchlist.tmdb.model.Movie
import kotlinx.coroutines.CoroutineDispatcher

public interface TmdbClient {

    public suspend fun getMovies(): Result<List<Movie>>
}

public fun TmdbClient(
    baseUrl: String,
    apiKey: String,
    dispatcher: CoroutineDispatcher,
): TmdbClient {
    val tmdbApi = TmdbApi(baseUrl, apiKey)
    return TmdbClientImpl(tmdbApi, dispatcher)
}