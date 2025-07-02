package com.watchlist.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import me.saket.telephoto.zoomable.coil3.ZoomableAsyncImage

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MoviePoster(posterLink: String, contentScale: ContentScale, modifier: Modifier = Modifier) {
    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
        AsyncImage(
            model = "http://image.tmdb.org/t/p/w300$posterLink",
            contentDescription = null, // TODO
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

@Composable
fun MoviePosterFullscreen(posterLink: String, modifier: Modifier = Modifier) {
    val barsVisibilityController = LocalBarsVisibilityController.current
    barsVisibilityController.hideAll()
    barsVisibilityController.showOnDispose()
    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.Black)) {
        ZoomableAsyncImage(
            model = "http://image.tmdb.org/t/p/w300/$posterLink",
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )
    }
}