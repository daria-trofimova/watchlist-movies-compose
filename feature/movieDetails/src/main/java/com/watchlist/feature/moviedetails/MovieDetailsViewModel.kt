package com.watchlist.feature.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watchlist.data.movies.MoviesRepository
import com.watchlist.data.movies.model.Movie
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
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _state: MutableStateFlow<MovieDetailUiState> =
        MutableStateFlow(MovieDetailUiState.Initial())
    val state: StateFlow<MovieDetailUiState> = _state

    init {
        viewModelScope.launch {
            moviesRepository.getMovieStream(id = movieId).collect {
                _state.emit(MovieDetailUiState.Success(it))
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(movieId: Long): MovieDetailsViewModel
    }
}

internal sealed class MovieDetailUiState {
    class Initial : MovieDetailUiState()
    class Success(val movie: Movie) : MovieDetailUiState()
}