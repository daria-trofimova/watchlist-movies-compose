package com.watchlist.data.movies

import com.watchlist.database.Movie

internal fun com.watchlist.tmdb.model.Movie.toDatabaseModel(): Movie =
    Movie(
        id = id,
        title = title,
        overview = overview,
        rating = voteAverage,
        posterLink = posterPath,
    )