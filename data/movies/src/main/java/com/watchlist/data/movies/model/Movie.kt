package com.watchlist.data.movies.model

public data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val rating: Float,
    val posterLink: String,
) {
    public companion object {
        public fun from(movie: com.watchlist.database.Movie): Movie = Movie(
            id = movie.id,
            title = movie.title,
            overview = movie.overview,
            rating = movie.rating,
            posterLink = movie.posterLink,
        )
    }
}