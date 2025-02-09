package com.watchlist.movies.ui.movie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watchlist.movies.ui.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {

    private val _movie = MutableStateFlow(mockMovie)
    val movie: StateFlow<Movie> = _movie

    fun toggleFavorite() {
        viewModelScope.launch {
            val old = movie.value
            _movie.emit(old.copy(isFavorite = !old.isFavorite))
        }
    }
}

private val mockMovie =
    Movie(id = "id", title = "Titanic", rating = 2.1f, posterLink = "", isFavorite = false)