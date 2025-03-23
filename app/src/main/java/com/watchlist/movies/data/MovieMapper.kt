package com.watchlist.movies.data

import com.watchlist.database.Movie

fun com.watchlist.tmdb_api.models.Movie.toLocalModel(isFavorite: Boolean): Movie =
    Movie(
        id = id,
        title = title,
        overview = overview,
        rating = voteAverage,
        posterLink = posterPath,
        isFavorite = isFavorite,
    )