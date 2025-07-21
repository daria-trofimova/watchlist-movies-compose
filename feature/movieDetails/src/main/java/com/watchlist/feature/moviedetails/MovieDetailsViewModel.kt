package com.watchlist.feature.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watchlist.data.movies.Result
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
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<MovieDetailsUiState> =
        MutableStateFlow(MovieDetailsUiState.Initial())
    internal val state: StateFlow<MovieDetailsUiState> = _state

    init {
        viewModelScope.launch {
            getMovieDetailsUseCase(id = movieId).collect { result ->
                val state = MovieDetailsUiState.from(result)
                _state.emit(state)
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
    class Loading(val movieDetails: MovieDetails? = null) : MovieDetailsUiState()
    class Success(val movieDetails: MovieDetails) : MovieDetailsUiState()
    class Error(val error: Throwable, val movieDetails: MovieDetails? = null) :
        MovieDetailsUiState()

    companion object {
        fun from(result: Result<MovieDetails>): MovieDetailsUiState = when (result) {
            is Result.InProgress -> Loading(result.data)
            is Result.Success -> Success(result.data)
            is Result.Error -> Error(result.error ?: UnknownError(), result.data)
        }
    }
}