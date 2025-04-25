package com.watchlist.movies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watchlist.movies.domain.FetchMoviesUseCase
import com.watchlist.movies.domain.GetMoviesStreamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getMoviesStreamUseCase: GetMoviesStreamUseCase,
    private val fetchMoviesUseCase: FetchMoviesUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Initial())
    val state: StateFlow<State> = _state

    init {
        viewModelScope.launch {
            fetchMoviesUseCase()
        }
        viewModelScope.launch {
            getMoviesStreamUseCase().collect {
                _state.emit(State.Success((it.map { Movie.from(it) })))
            }
        }
    }
}

internal sealed class State {
    data class Initial(val movies: List<Movie> = emptyList()) : State()
    data class Loading(val movies: List<Movie> = emptyList()) : State()
    class Error(val movies: List<Movie> = emptyList()) : State()
    class Success(val movies: List<Movie>) : State()
}