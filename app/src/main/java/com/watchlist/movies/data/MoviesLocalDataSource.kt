package com.watchlist.movies.data

import com.watchlist.database.Movie
import com.watchlist.database.MoviesDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(
    private val database: MoviesDatabase,
) {

    suspend fun saveMovies(
        movies: List<Movie>,
    ) {
        database.moviesDao().insertAll(movies)
    }

    fun getMoviesStream(): Flow<List<Movie>> = database.moviesDao().loadAll()

    fun getFavoriteMoviesStream(): Flow<List<Movie>> = database.moviesDao().loadAllFavorite()

    fun getMovieStream(
        id: Long,
    ): Flow<Movie> = database.moviesDao().loadMovie(id)

    suspend fun setFavorite(
        id: Long,
        isFavorite: Boolean,
    ) {
        database.moviesDao().updateFavorite(id, isFavorite)
    }
}