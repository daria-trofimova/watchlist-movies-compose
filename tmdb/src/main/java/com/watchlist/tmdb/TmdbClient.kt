package com.watchlist.tmdb

import com.watchlist.tmdb.model.Movie
import kotlinx.coroutines.CoroutineDispatcher

public interface TmdbClient {

    public suspend fun getMovies(): Result<List<Movie>>
}

public fun TmdbClient(
    baseUrl: String,
    dispatcher: CoroutineDispatcher,
): TmdbClient {
    val tmdbApi = TmdbApi(baseUrl)
    return TmdbClientImpl(tmdbApi, dispatcher)
}