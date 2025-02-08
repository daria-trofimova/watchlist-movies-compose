package com.watchlist.movies.ui

data class Movie(
    val id: String,
    val title: String,
    val rating: Float,
    val posterLink: String,
    val isFavorite: Boolean,
)