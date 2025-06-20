package com.watchlist.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MoviePoster(posterLink: String, contentScale: ContentScale, modifier: Modifier = Modifier) {
    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        AsyncImage(
            model = "http://image.tmdb.org/t/p/w300$posterLink",
            contentDescription = null,
            contentScale = contentScale,
            modifier = modifier,
        )
    }
}

@Composable
@Preview(widthDp = 100, heightDp = 150)
internal fun MoviePosterPreview() {
    MoviePoster("", ContentScale.FillWidth)
}

@OptIn(ExperimentalCoilApi::class)
internal val previewHandler = AsyncImagePreviewHandler {
    ColorImage(Color.Gray.toArgb())
}