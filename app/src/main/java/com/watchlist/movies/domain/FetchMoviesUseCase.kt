package com.watchlist.movies.domain

import com.watchlist.movies.data.MoviesRepository
import javax.inject.Inject

class FetchMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke(onlyFavorite: Boolean = false) {
        if (onlyFavorite) {
            moviesRepository.fetchFavoriteMovies()
        } else {
            moviesRepository.fetchMovies()
        }
    }
}