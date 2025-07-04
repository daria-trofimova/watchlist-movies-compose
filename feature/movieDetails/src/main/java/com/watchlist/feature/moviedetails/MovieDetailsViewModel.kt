package com.watchlist.feature.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watchlist.data.movies.ImagesRepository
import com.watchlist.data.movies.MoviesRepository
import com.watchlist.data.movies.model.ImageSize
import com.watchlist.feature.moviedetails.model.MovieDetails
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = MovieDetailsViewModel.Factory::class)
internal class MovieDetailsViewModel @AssistedInject constructor(
    @Assisted private val movieId: Long,
    private val moviesRepository: MoviesRepository,
    private val imagesRepository: ImagesRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<MovieDetailsUiState> =
        MutableStateFlow(MovieDetailsUiState.Initial())
    internal val state: StateFlow<MovieDetailsUiState> = _state

    init {
        viewModelScope.launch {
            moviesRepository.getMovieStream(id = movieId).collect { movie ->
                val standardImage = imagesRepository.getImage(movie.posterLink, ImageSize.STANDARD)
                val highResImage = imagesRepository.getImage(movie.posterLink, ImageSize.HIGH_RES)
                val poster = MovieDetails.Poster(
                    standardLink = standardImage.link,
                    highResLink = highResImage.link
                )
                _state.emit(MovieDetailsUiState.Success(MovieDetails.from(movie, poster)))
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(movieId: Long): MovieDetailsViewModel
    }
}

internal sealed class MovieDetailsUiState {
    class Initial : MovieDetailsUiState()
    class Success(val movieDetails: MovieDetails) : MovieDetailsUiState()
    class Error(val error: Throwable, val movieDetails: MovieDetails? = null) :
        MovieDetailsUiState()
}