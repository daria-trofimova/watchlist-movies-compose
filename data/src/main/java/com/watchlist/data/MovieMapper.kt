package com.watchlist.data

import com.watchlist.database.Movie

internal fun com.watchlist.tmdb.model.Movie.toDatabaseModel(isFavorite: Boolean = false): Movie =
    Movie(
        id = id,
        title = title,
        overview = overview,
        rating = voteAverage,
        posterLink = posterPath,
        isFavorite = isFavorite,
    )