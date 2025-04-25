package com.watchlist.movies.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.watchlist.movies.R

internal sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorites : Screen("favorites")
    class TopLevelScreen(screen: Screen, @StringRes val label: Int, val icon: ImageVector) :
        Screen(screen.route)
}

internal val topLevelScreens = listOf(
    Screen.TopLevelScreen(Screen.Home, label = R.string.home, Icons.Default.Home),
    Screen.TopLevelScreen(Screen.Favorites, label = R.string.favorites, Icons.Default.Favorite)
)