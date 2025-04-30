package com.watchlist.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watchlist.data.movies.Result
import com.watchlist.home.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Initial())
    val state: StateFlow<State> = _state

    init {
        viewModelScope.launch {
            getMoviesUseCase().collect { result ->
                _state.emit(State.from(result))
            }
        }
    }
}

sealed class State {
    data class Initial(val movies: List<Movie> = emptyList()) : State()
    data class Loading(val movies: List<Movie> = emptyList()) : State()
    class Error(val error: Throwable, val movies: List<Movie> = emptyList()) : State()
    class Success(val movies: List<Movie>) : State()

    companion object {
        fun from(result: Result<List<com.watchlist.data.movies.model.Movie>>): State =
            when (result) {
            is Result.InProgress -> Loading(result.data?.map { Movie.from(it) } ?: emptyList())
            is Result.Error -> Error(
                error = result.error ?: Error("Unknown error"),
                result.data?.map { Movie.from(it) } ?: emptyList())

            is Result.Success -> Success(result.data.map { Movie.from(it) })
        }
    }
}