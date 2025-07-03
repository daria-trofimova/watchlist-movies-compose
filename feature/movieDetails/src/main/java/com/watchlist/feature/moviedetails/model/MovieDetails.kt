package com.watchlist.feature.moviedetails.model

internal data class MovieDetails(
    val id: Long,
    val title: String,
    val overview: String,
    val rating: Float,
    val poster: Poster,
) {
    class Poster(val standardLink: String)

    companion object {
        fun from(movie: com.watchlist.data.movies.model.Movie, poster: Poster): MovieDetails =
            MovieDetails(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                rating = movie.rating,
                poster = poster,
            )
    }
}