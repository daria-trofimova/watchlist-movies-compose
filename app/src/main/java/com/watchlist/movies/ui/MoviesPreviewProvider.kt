package com.watchlist.movies.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class MoviesPreviewProvider : PreviewParameterProvider<List<Movie>> {
    override val values: Sequence<List<Movie>> = sequenceOf(
        listOf(
            Movie(
                id = 100,
                title = "Titanic",
                overview = "",
                rating = 1.0f,
                posterLink = "",
                isFavorite = false
            ),
            Movie(
                id = 101,
                title = "Titanic 1",
                overview = "",
                rating = 1.0f,
                posterLink = "",
                isFavorite = false
            ),
            Movie(
                id = 102,
                title = "Titanic",
                overview = "",
                rating = 1.0f,
                posterLink = "",
                isFavorite = false
            ),
            Movie(
                id = 103,
                title = "Titanic",
                overview = "",
                rating = 1.0f,
                posterLink = "",
                isFavorite = false
            )
        )
    )
}
