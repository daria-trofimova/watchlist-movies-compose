package com.watchlist.home.model

import java.util.Locale

data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val rating: Float,
    val posterLink: String,
) {

    val ratingFormatted: String get() = String.format(Locale.US, "%.2f", rating)

    companion object {
        fun from(movie: com.watchlist.data.movies.model.Movie): Movie =
            Movie(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                rating = movie.rating,
                posterLink = movie.posterLink,
            )
    }
}