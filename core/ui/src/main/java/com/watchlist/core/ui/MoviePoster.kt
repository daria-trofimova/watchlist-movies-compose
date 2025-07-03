package com.watchlist.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import me.saket.telephoto.zoomable.coil3.ZoomableAsyncImage
import kotlin.math.abs
import kotlin.math.roundToInt

@OptIn(ExperimentalCoilApi::class)
@Composable
public fun MoviePoster(
    posterLink: String,
    contentScale: ContentScale,
    modifier: Modifier = Modifier
) {
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
public fun MoviePosterFullscreen(
    posterLink: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val barsVisibilityController = LocalBarsVisibilityController.current
    barsVisibilityController.hideAll()
    barsVisibilityController.showOnDispose()

    var offsetY by remember { mutableFloatStateOf(0f) }
    val density = LocalDensity.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .offset { IntOffset(0, offsetY.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        val dismissThreshold = with(density) { 150.dp.toPx() }
                        if (abs(offsetY) > dismissThreshold) {
                            onDismiss()
                        } else {
                            offsetY = 0f
                        }
                    }
                ) { _, dragAmount ->
                    offsetY += dragAmount.y
                }
            }
    ) {
        ZoomableAsyncImage(
            model = "http://image.tmdb.org/t/p/w300/$posterLink",
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )
    }
}