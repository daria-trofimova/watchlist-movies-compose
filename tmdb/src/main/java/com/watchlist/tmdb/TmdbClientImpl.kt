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
            try {
                val response = tmdbApi.getPopularMovies()
                if (response.isSuccessful) {
                    Result.success(response.body()!!.results)
                } else {
                    Result.failure(Exception("Error getting popular movies: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}


