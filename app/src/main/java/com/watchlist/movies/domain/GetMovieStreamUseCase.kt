package com.watchlist.movies.domain

import com.watchlist.movies.data.Movie
import com.watchlist.movies.data.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieStreamUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    operator fun invoke(id: Long): Flow<Movie> = moviesRepository.getMovieStream(id)
}