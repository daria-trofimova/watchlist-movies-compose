package com.watchlist.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watchlist.data.movies.Result
import com.watchlist.feature.home.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Initial())
    val state: StateFlow<HomeUiState> = _state

    init {
        viewModelScope.launch {
            getMoviesUseCase().collect { result ->
                _state.emit(HomeUiState.from(result))
            }
        }
    }
}

sealed class HomeUiState {
    data class Initial(val movies: List<Movie> = emptyList()) : HomeUiState()
    data class Loading(val movies: List<Movie> = emptyList()) : HomeUiState()
    class Error(val error: Throwable, val movies: List<Movie> = emptyList()) : HomeUiState()
    class Success(val movies: List<Movie>) : HomeUiState()

    companion object {
        fun from(result: Result<List<com.watchlist.data.movies.model.Movie>>): HomeUiState =
            when (result) {
            is Result.InProgress -> Loading(result.data?.map { Movie.from(it) } ?: emptyList())
            is Result.Error -> Error(
                error = result.error ?: Error("Unknown error"),
                result.data?.map { Movie.from(it) } ?: emptyList())

            is Result.Success -> Success(result.data.map { Movie.from(it) })
        }
    }
}