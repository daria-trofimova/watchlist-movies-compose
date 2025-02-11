package com.watchlist.movies.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey val id: Long,
    val title: String,
    val overview: String,
    val rating: Float,
    val posterLink: String,
    var isFavorite: Boolean,
) {

    companion object {
        fun from(movie: MovieResponse, isFavorite: Boolean): Movie =
            Movie(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                rating = movie.voteAverage,
                posterLink = movie.posterPath,
                isFavorite = isFavorite,
            )
    }
}