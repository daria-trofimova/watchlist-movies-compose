package com.watchlist.feature.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.watchlist.feature.home.model.Movie

internal class MoviesPreviewProvider : PreviewParameterProvider<List<Movie>> {
    override val values: Sequence<List<Movie>> = sequenceOf(
        listOf(
            Movie(
                id = 100,
                title = "Titanic",
                rating = 1.0f,
                poster = Movie.Poster(""),
            ),
            Movie(
                id = 101,
                title = " Titanic 1 Titanic 1 Titanic 1 Titanic 1 Titanic 1 Titanic 1",
                rating = 1.0f,
                poster = Movie.Poster(""),
            ),
            Movie(
                id = 102,
                title = "Titanic 2",
                rating = 1.0f,
                poster = Movie.Poster(""),
            ),
            Movie(
                id = 103,
                title = "Titanic 3",
                rating = 1.0f,
                poster = Movie.Poster(""),
            )
        )
    )
}
