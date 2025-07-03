package com.watchlist.feature.home

import com.watchlist.data.movies.ImagesRepository
import com.watchlist.data.movies.MoviesRepository
import com.watchlist.data.movies.Result
import com.watchlist.data.movies.mapData
import com.watchlist.data.movies.model.ImageSize
import com.watchlist.feature.home.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

public class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val imagesRepository: ImagesRepository,
) {
    public operator fun invoke(): Flow<Result<List<Movie>>> =
        moviesRepository.getMovies().map { result ->
            result.mapData { movies ->
                movies.map { movie ->
                    val image = imagesRepository.getImage(movie.posterLink, ImageSize.PREVIEW)
                    Movie.from(movie, image)
                }
            }
        }
}