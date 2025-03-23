package com.watchlist.movies.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(
    private val dao: MoviesDao,
) {

    suspend fun saveMovies(
        movies: List<Movie>,
    ) {
        dao.insertAll(movies)
    }

    fun getMoviesStream(): Flow<List<Movie>> = dao.loadAll()

    fun getFavoriteMoviesStream(): Flow<List<Movie>> = dao.loadAllFavorite()

    fun getMovieStream(
        id: Long,
    ): Flow<Movie> = dao.loadMovie(id)

    suspend fun setFavorite(
        id: Long,
        isFavorite: Boolean,
    ) {
        dao.updateFavorite(id, isFavorite)
    }
}