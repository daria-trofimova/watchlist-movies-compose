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
public fun MoviePoster(
    imageLink: String,
    contentScale: ContentScale,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        AsyncImage(
            model = imageLink,
            contentDescription = null, // TODO
            contentScale = contentScale,
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
internal val previewHandler = AsyncImagePreviewHandler {
    ColorImage(Color.Gray.toArgb())
}

@Composable
@Preview
internal fun MoviePosterPreview() {
    MoviePoster("", ContentScale.FillWidth)
}