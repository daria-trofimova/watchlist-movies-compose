package com.watchlist.tmdb

import com.watchlist.tmdb.model.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class TmdbClientImpl(
    private val tmdbApi: TmdbApi,
    private val dispatcher: CoroutineDispatcher,
) : TmdbClient {

    override suspend fun getMovies(): Result<List<Movie>> =
        withContext(dispatcher) {
            runCatching {
                val response = tmdbApi.getPopularMovies()
                if (response.isSuccessful) {
                    response.body()?.results ?: emptyList()
                } else {
                    throw Exception("Error getting popular movies: ${response.code()}")
                }
            }
        }

    override suspend fun getMovie(id: Long): Result<Movie> =
        withContext(dispatcher) {
            runCatching {
                val response = tmdbApi.getMovie(id)
                if (response.isSuccessful) {
                    response.body() ?: throw Exception("Movie not found")
                } else {
                    throw Exception("Error getting movie: ${response.code()}")
                }
            }
        }
}


