package com.watchlist.movies.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoritesViewModel : ViewModel() {

    private val _movies: MutableStateFlow<List<Movie>> = MutableStateFlow(mockMovies)
    val movies: StateFlow<List<Movie>> = _movies
}

private val mockMovies = listOf(
    Movie(id = "2", title = "Titanic 2", rating = 2.2f, posterLink = ""),
    Movie(id = "3", title = "Titanic 3", rating = 3.2f, posterLink = "")
)