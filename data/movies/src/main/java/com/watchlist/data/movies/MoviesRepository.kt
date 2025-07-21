package com.watchlist.data.movies

import com.watchlist.data.movies.model.Movie
import com.watchlist.database.MoviesDatabase
import com.watchlist.tmdb.TmdbClient
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

public class MoviesRepository @Inject constructor(
    private val moviesDatabase: MoviesDatabase,
    private val tmdbClient: TmdbClient,
) {

    public fun getMovies(): Flow<Result<List<Movie>>> =
        flow {
            val cachedMovies =
                moviesDatabase.moviesDao().loadAll().map { it.map { Movie.from(it) } }.firstOrNull()
            emit(Result.InProgress(cachedMovies))
            val result = tmdbClient.getMovies()
            when {
                result.isSuccess -> {
                    val movies = result.getOrThrow().map { it.toDatabaseModel() }
                    moviesDatabase.moviesDao().insertAll(movies)
                    emit(Result.Success(movies.map { Movie.from(it) }))
                }
                result.isFailure -> {
                    emit(Result.Error(error = result.exceptionOrNull(), data = cachedMovies))
                }
            }
        }

    public fun getMovie(
        id: Long,
    ): Flow<Result<Movie>> = flow {
        val cachedMovie =
            moviesDatabase.moviesDao().loadMovie(id).map { Movie.from(it) }.firstOrNull()
        emit(Result.InProgress(cachedMovie))
        val result = tmdbClient.getMovie(id)
        when {
            result.isSuccess -> {
                val movie = result.getOrThrow().toDatabaseModel()
                moviesDatabase.moviesDao().insert(movie)
                emit(Result.Success(Movie.from(movie)))
            }

            result.isFailure -> {
                emit(Result.Error(error = result.exceptionOrNull(), data = cachedMovie))
            }
        }
    }
}