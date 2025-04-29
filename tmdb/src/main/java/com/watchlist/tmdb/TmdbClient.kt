package com.watchlist.tmdb

import com.watchlist.tmdb.model.Movie
import kotlinx.coroutines.CoroutineDispatcher

interface TmdbClient {

    suspend fun getMovies(): Result<List<Movie>>

    suspend fun getFavoriteMovies(): Result<List<Movie>>

    suspend fun setFavorite(id: Long, isFavorite: Boolean): Result<Unit>
}

fun TmdbClient(
    baseUrl: String,
    dispatcher: CoroutineDispatcher,
): TmdbClient {
    val tmdbApi = TmdbApi(baseUrl)
    return TmdbClientImpl(tmdbApi, dispatcher)
}