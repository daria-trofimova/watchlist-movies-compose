package com.watchlist.movies.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieDetailViewModel : ViewModel() {

    private val _movie = MutableStateFlow(mockMovie)
    val movie: StateFlow<Movie> = _movie
}

private val mockMovie = Movie(id = "id", title = "Titanic", rating = 2.1f, posterLink = "")