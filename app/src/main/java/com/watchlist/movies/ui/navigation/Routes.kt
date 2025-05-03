package com.watchlist.movies.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.watchlist.movies.R

internal sealed class Screen(val route: String, @StringRes val title: Int) {
    object Home : Screen("home", title = R.string.home)
    object Favorites : Screen("favorites", title = R.string.favorites)
    class MovieDetails : Screen("$path/{$parameter}", title = R.string.movie_details) {
        companion object {
            const val path: String = "movieDetails"
            const val parameter: String = "MOVIE_ID"
        }
    }

    class TopLevelScreen(screen: Screen, @StringRes val label: Int, val icon: ImageVector) :
        Screen(screen.route, screen.title)

    companion object {
        fun from(route: String): Screen = when {
            route == Home.route -> Home
            route == Favorites.route -> Favorites
            route.startsWith(MovieDetails.path) -> MovieDetails()
            else -> throw Throwable("Unknown route")
        }
    }
}

internal val topLevelScreens = listOf(
    Screen.TopLevelScreen(Screen.Home, label = R.string.home, Icons.Default.Home),
    Screen.TopLevelScreen(Screen.Favorites, label = R.string.favorites, Icons.Default.Favorite)
)