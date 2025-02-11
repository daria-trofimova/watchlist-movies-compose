package com.watchlist.movies.ui.movie.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watchlist.movies.data.MoviesRepository
import com.watchlist.movies.ui.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> = _movie

    init {
        val id = savedState.get<Long>("id")!!
        viewModelScope.launch {
            moviesRepository.getMovie(id).collect {
                _movie.emit(Movie.from(it))
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            movie.value?.let {
                moviesRepository.setFavorite(id = it.id, !it.isFavorite)
            }
        }
    }
}