package com.watchlist.movies.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val localDataSource: MoviesLocalDataSource,
    private val remoteDataSource: MoviesRemoteDataSource,
) {
    val movies: Flow<List<Movie>> = localDataSource.getMoviesStream()

    val favoriteMovies: Flow<List<Movie>> = localDataSource.getFavoriteMoviesStream()

    suspend fun fetchMovies() {
        val movies = remoteDataSource.getMovies()
        localDataSource.saveMovies(movies.map { Movie.from(it, isFavorite = false) })
    }

    suspend fun fetchFavoriteMovies() {
        val movies = remoteDataSource.getFavoriteMovies()
        localDataSource.saveMovies(movies.map { Movie.from(it, isFavorite = true) })
    }

    fun getMovieStream(id: Long): Flow<Movie> = localDataSource.getMovieStream(id)

    suspend fun setFavorite(id: Long, isFavorite: Boolean) {
        remoteDataSource.setFavorite(id, isFavorite)
        localDataSource.setFavorite(id, isFavorite)
    }
}