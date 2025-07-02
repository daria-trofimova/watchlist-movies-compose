package com.watchlist.feature.home

import com.watchlist.data.movies.MoviesRepository
import com.watchlist.data.movies.Result
import com.watchlist.data.movies.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
) {
    operator fun invoke(): Flow<Result<List<Movie>>> = moviesRepository.getMovies()
}