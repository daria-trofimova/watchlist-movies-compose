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
public class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Initial())
    public val state: StateFlow<HomeUiState> = _state

    init {
        viewModelScope.launch {
            getMoviesUseCase().collect { result ->
                _state.emit(HomeUiState.from(result))
            }
        }
    }
}

public sealed class HomeUiState {
    public data class Initial(val movies: List<Movie> = emptyList()) : HomeUiState()
    public data class Loading(val movies: List<Movie> = emptyList()) : HomeUiState()
    public class Error(public val error: Throwable, public val movies: List<Movie> = emptyList()) :
        HomeUiState()

    public class Success(public val movies: List<Movie>) : HomeUiState()

    public companion object {
        public fun from(result: Result<List<com.watchlist.data.movies.model.Movie>>): HomeUiState =
            when (result) {
            is Result.InProgress -> Loading(result.data?.map { Movie.from(it) } ?: emptyList())
            is Result.Error -> Error(
                error = result.error ?: Error("Unknown error"),
                result.data?.map { Movie.from(it) } ?: emptyList())

            is Result.Success -> Success(result.data.map { Movie.from(it) })
        }
    }
}