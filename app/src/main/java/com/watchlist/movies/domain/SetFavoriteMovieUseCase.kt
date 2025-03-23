package com.watchlist.movies.domain

import com.watchlist.movies.data.MoviesRepository
import javax.inject.Inject

class SetFavoriteMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
) {

    suspend operator fun invoke(
        id: Long,
        isFavorite: Boolean,
    ) {
        moviesRepository.setFavorite(id, isFavorite)
    }
}