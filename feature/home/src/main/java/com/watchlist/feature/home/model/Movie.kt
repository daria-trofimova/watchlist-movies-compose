package com.watchlist.feature.home.model

import com.watchlist.data.movies.model.Image
import java.util.Locale

public data class Movie(
    val id: Long,
    val title: String,
    val rating: Float,
    val poster: Poster,
) {

    public class Poster(public val previewLink: String)

    val ratingFormatted: String get() = String.format(Locale.US, "%.2f", rating)

    public companion object {
        public fun from(movie: com.watchlist.data.movies.model.Movie, image: Image): Movie =
            Movie(
                id = movie.id,
                title = movie.title,
                rating = movie.rating,
                poster = Poster(image.link),
            )
    }
}