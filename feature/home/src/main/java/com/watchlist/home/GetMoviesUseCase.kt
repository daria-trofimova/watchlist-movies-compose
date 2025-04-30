package com.watchlist.home

import com.watchlist.data.MoviesRepository
import com.watchlist.data.Result
import com.watchlist.data.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
) {
    operator fun invoke(): Flow<Result<List<Movie>>> = moviesRepository.getMovies()
}