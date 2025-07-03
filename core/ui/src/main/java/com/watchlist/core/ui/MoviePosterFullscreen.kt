package com.watchlist.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import me.saket.telephoto.zoomable.coil3.ZoomableAsyncImage
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
public fun MoviePosterFullscreen(
    imageLink: String,
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
            model = imageLink,
            contentDescription = null, // TODO
            modifier = Modifier.fillMaxSize(),
        )
    }
}