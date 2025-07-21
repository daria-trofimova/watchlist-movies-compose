package com.watchlist.feature.moviedetails

import com.watchlist.data.movies.ImagesRepository
import com.watchlist.data.movies.MoviesRepository
import com.watchlist.data.movies.Result
import com.watchlist.data.movies.mapData
import com.watchlist.data.movies.model.ImageSize
import com.watchlist.feature.moviedetails.model.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class GetMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val imagesRepository: ImagesRepository,
) {

    operator fun invoke(id: Long): Flow<Result<MovieDetails>> =
        moviesRepository.getMovie(id).map { result ->
            result.mapData { movie ->
                val standardImage = imagesRepository.getImage(movie.posterLink, ImageSize.STANDARD)
                val highResImage = imagesRepository.getImage(movie.posterLink, ImageSize.HIGH_RES)
                val poster = MovieDetails.Poster(
                    standardLink = standardImage.link,
                    highResLink = highResImage.link
                )
                MovieDetails.from(movie, poster)
            }
        }
}