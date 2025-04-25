package com.watchlist.movies.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler

@OptIn(ExperimentalCoilApi::class)
@Composable
internal fun MoviePoster(posterLink: String, modifier: Modifier = Modifier) {
    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        AsyncImage(
            model = imagesBaseUrl + posterLink,
            contentDescription = null,
            modifier = modifier,
        )
    }
}


@Composable
@Preview(widthDp = 100, heightDp = 150)
internal fun MoviePosterPreview() {
    MoviePoster("")
}

@OptIn(ExperimentalCoilApi::class)
internal val previewHandler = AsyncImagePreviewHandler {
    ColorImage(Color.Gray.toArgb())
}

const val imagesBaseUrl = "http://image.tmdb.org/t/p/w500"