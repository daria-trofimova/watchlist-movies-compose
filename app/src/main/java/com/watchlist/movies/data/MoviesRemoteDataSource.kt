package com.watchlist.movies.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(private val tmdbApi: TmdbApi) {

    suspend fun getMovies(): List<MovieResponse> =
        withContext(Dispatchers.IO) {
            val response = tmdbApi.getPopularMovies()
            response.body()!!.results
        }

    suspend fun getFavoriteMovies(): List<MovieResponse> =
        withContext(Dispatchers.IO) {
            val response = tmdbApi.getFavoriteMovies()
            response.body()!!.results
        }

    suspend fun setFavorite(id: Long, isFavorite: Boolean) {
        withContext(Dispatchers.IO) {
            val body =
                SetFavoriteRequestBody(mediaType = "movie", mediaId = id, isFavorite = isFavorite)
            tmdbApi.setFavoriteMovie(body)
        }
    }
}