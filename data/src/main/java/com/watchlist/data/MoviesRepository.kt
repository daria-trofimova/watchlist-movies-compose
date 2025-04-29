package com.watchlist.data

import com.watchlist.data.model.Movie
import com.watchlist.database.MoviesDatabase
import com.watchlist.tmdb_api.TmdbClient
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepository @Inject constructor(
    private val moviesDatabase: MoviesDatabase,
    private val tmdbClient: TmdbClient,
) {

    val movies: Flow<List<Movie>> =
        moviesDatabase.moviesDao().loadAll().map { it.map { Movie.from(it) } }

    val favoriteMovies: Flow<List<Movie>> =
        moviesDatabase.moviesDao().loadAllFavorite().map { it.map { Movie.from(it) } }

    suspend fun fetchMovies() {
        val movies = tmdbClient.getMovies().getOrThrow()
        moviesDatabase.moviesDao().insertAll(movies.map { it.toDatabaseModel(isFavorite = false) })
    }

    suspend fun fetchFavoriteMovies() {
        val movies = tmdbClient.getFavoriteMovies().getOrThrow()
        moviesDatabase.moviesDao().insertAll(movies.map { it.toDatabaseModel(isFavorite = true) })
    }

    fun getMovieStream(
        id: Long,
    ): Flow<Movie> = moviesDatabase.moviesDao().loadMovie(id).map { Movie.from(it) }

    suspend fun setFavorite(
        id: Long,
        isFavorite: Boolean,
    ) {
        tmdbClient.setFavorite(id, isFavorite)
        moviesDatabase.moviesDao().updateFavorite(id, isFavorite)
    }
}