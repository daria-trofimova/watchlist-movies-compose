package com.watchlist.tmdb_api

import com.watchlist.tmdb_api.model.MediaType
import com.watchlist.tmdb_api.model.Movie
import com.watchlist.tmdb_api.model.SetFavoriteRequestBody
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

    override suspend fun getFavoriteMovies(): Result<List<Movie>> =
        withContext(dispatcher) {
            try {
                val response = tmdbApi.getFavoriteMovies()
                if (response.isSuccessful) {
                    Result.success(response.body()!!.results)
                } else {
                    Result.failure(Exception("Error getting favorite movies: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun setFavorite(
        id: Long,
        isFavorite: Boolean,
    ): Result<Unit> =
        withContext(dispatcher) {
            try {
                val body =
                    SetFavoriteRequestBody(
                        mediaType = MediaType.MOVIE,
                        mediaId = id,
                        isFavorite = isFavorite,
                    )
                val response = tmdbApi.setFavoriteMovie(body)
                if (response.isSuccessful) {
                    Result.success(Unit)
                } else {
                    Result.failure(Exception("Error getting favorite movies: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}


