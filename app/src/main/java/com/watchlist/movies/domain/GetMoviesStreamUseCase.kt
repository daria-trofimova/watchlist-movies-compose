package com.watchlist.movies.domain

import com.watchlist.data.MoviesRepository
import com.watchlist.data.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesStreamUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
) {

    operator fun invoke(
        onlyFavorite: Boolean = false,
    ): Flow<List<Movie>> =
        if (onlyFavorite) moviesRepository.favoriteMovies else moviesRepository.movies
}