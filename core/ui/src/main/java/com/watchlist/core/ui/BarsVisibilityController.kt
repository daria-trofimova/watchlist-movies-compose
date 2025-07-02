package com.watchlist.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

public val LocalBarsVisibilityController: ProvidableCompositionLocal<BarsVisibilityController> =
    compositionLocalOf {
        error("BarsVisibilityController not provided.")
    }

// TODO: implement Saver for rememberSaveable
public class BarsVisibilityController {
    public val topBar: BarVisibilityController = BarVisibilityController()
    public val bottomBar: BarVisibilityController = BarVisibilityController()

    public fun hideAll() {
        topBar.hide()
        bottomBar.hide()
    }

    public fun showAll() {
        topBar.show()
        bottomBar.show()
    }
}

public class BarVisibilityController {
    public var isVisible: Boolean by mutableStateOf(true)
        private set

    public fun show() {
        isVisible = true
    }

    public fun hide() {
        isVisible = false
    }
}

@Composable
public fun BarsVisibilityController.showOnDispose() {
    DisposableEffect(Unit) {
        onDispose { showAll() }
    }
}