package com.watchlist.movies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watchlist.movies.domain.GetMoviesStreamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getMoviesStreamUseCase: GetMoviesStreamUseCase,
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Initial)
    val state: StateFlow<State> = _state

    init {
        viewModelScope.launch {
            getMoviesStreamUseCase().collect {
                delay(5000)
                _state.emit(State.Loading)
                delay(5000)
                _state.emit(State.Success((it.map { Movie.from(it) })))
            }
        }
    }
}

internal sealed class State {
    data object Initial : State()
    data object Loading : State()
    class Error : State()
    class Success(val movies: List<Movie>) : State()
}