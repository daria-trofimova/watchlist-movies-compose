package com.watchlist.movies.data

import com.watchlist.movies.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val tmdbApi: TmdbApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend fun getMovies(): List<MovieResponse> =
        withContext(dispatcher) {
            val response = tmdbApi.getPopularMovies()
            response.body()!!.results
        }

    suspend fun getFavoriteMovies(): List<MovieResponse> =
        withContext(dispatcher) {
            val response = tmdbApi.getFavoriteMovies()
            response.body()!!.results
        }

    suspend fun setFavorite(id: Long, isFavorite: Boolean) {
        withContext(dispatcher) {
            val body =
                SetFavoriteRequestBody(
                    mediaType = MEDIA_TYPE_MOVIE,
                    mediaId = id,
                    isFavorite = isFavorite
                )
            tmdbApi.setFavoriteMovie(body)
        }
    }
}