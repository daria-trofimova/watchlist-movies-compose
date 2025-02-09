package com.watchlist.movies.ui

data class Movie(
    val id: String,
    val title: String,
    val rating: Float,
    val posterLink: String,
    val isFavorite: Boolean,
) {
    companion object {
        fun from(movie: com.watchlist.movies.data.Movie): Movie {
            return Movie(
                id = movie.id,
                title = movie.title,
                rating = movie.rating,
                posterLink = movie.posterLink,
                isFavorite = movie.isFavorite
            )
        }
    }
}