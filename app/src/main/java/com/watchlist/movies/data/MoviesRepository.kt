package com.watchlist.movies.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val localDataSource: MoviesLocalDataSource,
    private val remoteDataSource: MoviesRemoteDataSource,
) {

    val movies: Flow<List<Movie>> = flowOf(mockMovies)
}

private val mockMovies = listOf(
    Movie(id = "1", title = "Titanic", rating = 1.2f, posterLink = "", isFavorite = true),
    Movie(id = "2", title = "Titanic 2", rating = 2.2f, posterLink = "", isFavorite = true),
    Movie(id = "3", title = "Titanic 3", rating = 3.2f, posterLink = "", isFavorite = false),
    Movie(id = "4", title = "Titanic 4", rating = 4.2f, posterLink = "", isFavorite = false),
    Movie(id = "5", title = "Titanic 5", rating = 5.2f, posterLink = "", isFavorite = false)
)