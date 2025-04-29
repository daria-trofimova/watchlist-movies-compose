package com.watchlist.movies.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.watchlist.movies.ui.model.Movie

internal class MoviesPreviewProvider : PreviewParameterProvider<List<Movie>> {
    override val values: Sequence<List<Movie>> = sequenceOf(
        listOf(
            Movie(
                id = 100,
                title = "Titanic",
                overview = "",
                rating = 1.0f,
                posterLink = "",
            ),
            Movie(
                id = 101,
                title = " Titanic 1 Titanic 1 Titanic 1 Titanic 1 Titanic 1 Titanic 1",
                overview = "",
                rating = 1.0f,
                posterLink = "",
            ),
            Movie(
                id = 102,
                title = "Titanic 2",
                overview = "",
                rating = 1.0f,
                posterLink = "",
            ),
            Movie(
                id = 103,
                title = "Titanic 3",
                overview = "",
                rating = 1.0f,
                posterLink = "",
            )
        )
    )
}
