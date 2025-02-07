package com.watchlist.movies.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    private val _movies: MutableStateFlow<List<Movie>> = MutableStateFlow(mockMovies)
    val movies: StateFlow<List<Movie>> = _movies
}

private val mockMovies = listOf(
    Movie(id = "1", title = "Titanic", rating = 1.2f, posterLink = ""),
    Movie(id = "2", title = "Titanic 2", rating = 2.2f, posterLink = ""),
    Movie(id = "3", title = "Titanic 3", rating = 3.2f, posterLink = ""),
    Movie(id = "4", title = "Titanic 4", rating = 4.2f, posterLink = ""),
    Movie(id = "5", title = "Titanic 5", rating = 5.2f, posterLink = "")
)