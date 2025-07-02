package com.watchlist.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

val LocalBarsVisibilityController = compositionLocalOf<BarsVisibilityController> {
    error("BarsVisibilityController not provided.")
}

// TODO: implement Saver for rememberSaveable
class BarsVisibilityController {
    val topBar = BarVisibilityController()
    val bottomBar = BarVisibilityController()

    fun hideAll() {
        topBar.hide()
        bottomBar.hide()
    }

    fun showAll() {
        topBar.show()
        bottomBar.show()
    }
}

class BarVisibilityController {
    var isVisible: Boolean by mutableStateOf(true)
        private set

    fun show() {
        isVisible = true
    }

    fun hide() {
        isVisible = false
    }
}

@Composable
fun BarsVisibilityController.showOnDispose() {
    DisposableEffect(Unit) {
        onDispose { showAll() }
    }
}