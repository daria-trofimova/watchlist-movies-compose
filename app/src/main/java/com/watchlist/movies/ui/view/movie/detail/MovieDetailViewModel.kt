package com.watchlist.movies.ui.view.movie.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watchlist.movies.domain.GetMovieStreamUseCase
import com.watchlist.movies.domain.SetFavoriteMovieUseCase
import com.watchlist.movies.ui.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val getMovieStreamUseCase: GetMovieStreamUseCase,
    private val setFavoriteMovieUseCase: SetFavoriteMovieUseCase,
) : ViewModel() {

    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> = _movie

    init {
        val id = savedState.get<Long>("id")!!
        viewModelScope.launch {
            getMovieStreamUseCase(id = id).collect {
                _movie.emit(Movie.from(it))
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            movie.value?.let {
                setFavoriteMovieUseCase(id = it.id, !it.isFavorite)
            }
        }
    }
}