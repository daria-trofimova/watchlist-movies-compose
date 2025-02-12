package com.watchlist.movies.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(private val dao: MovieDao) {

    suspend fun saveMovies(movies: List<Movie>) {
        withContext(Dispatchers.IO) {
            dao.insertAll(movies)
        }
    }

    fun getMoviesStream(): Flow<List<Movie>> = dao.loadAll()

    fun getFavoriteMoviesStream(): Flow<List<Movie>> = dao.loadAllFavorite()

    fun getMovieStream(id: Long): Flow<Movie> = dao.loadMovie(id)

    suspend fun setFavorite(id: Long, isFavorite: Boolean) {
        withContext(Dispatchers.IO) {
            dao.updateFavorite(id, isFavorite)
        }
    }
}