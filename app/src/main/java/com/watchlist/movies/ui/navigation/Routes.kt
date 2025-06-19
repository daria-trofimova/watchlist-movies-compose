package com.watchlist.movies.ui.navigation

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.watchlist.movies.R

internal sealed class Screen(val route: String) {

    sealed interface Title {
        @JvmInline
        value class Resource(@StringRes val id: Int) : Title

        @JvmInline
        value class Text(val text: String) : Title

        @Composable
        fun asString(): String = when (this) {
            is Resource -> stringResource(id)
            is Text -> text
        }
    }

    abstract val title: Title

    abstract class NavigationBarScreen(route: String, val icon: ImageVector) : Screen(route)

    data object Home : NavigationBarScreen(route = "home", icon = Icons.Default.Home) {
        override val title: Title = Title.Resource(R.string.home)
    }

    data object Favorites :
        NavigationBarScreen(route = "favorites", icon = Icons.Default.Favorite) {
        override val title: Title = Title.Resource(R.string.favorites)
    }

    class MovieDetails(val id: Long, title: String) : Screen(route = ROUTE) {
        override val title: Title = Title.Text(title)

        companion object {
            const val ROUTE = "movieDetails"
            const val ID_ARGUMENT_KEY = "id"
            const val TITLE_ARGUMENT_KEY = "title"
            const val ROUTE_PATTERN = "$ROUTE/{$ID_ARGUMENT_KEY}/{$TITLE_ARGUMENT_KEY}"
            fun createRoute(id: Long, title: String): String {
                return "$ROUTE/$id/$title"
            }
        }
    }

    companion object {
        fun from(route: String, arguments: Bundle? = null): Screen =
            when {
                route == Home.route -> Home
                route == Favorites.route -> Favorites
                route.startsWith(MovieDetails.ROUTE) -> {
                    val id = arguments?.getLong(MovieDetails.ID_ARGUMENT_KEY)
                        ?: throw IllegalStateException("Missing required argument: ${MovieDetails.ID_ARGUMENT_KEY}")
                    val title = arguments.getString(MovieDetails.TITLE_ARGUMENT_KEY)
                        ?: throw IllegalStateException("Missing required argument: ${MovieDetails.TITLE_ARGUMENT_KEY}")
                    MovieDetails(id, title)
                }

                else -> throw IllegalArgumentException("Unknown route: $route")
            }
    }
}

internal val navigationBarScreens = listOf(Screen.Home, Screen.Favorites)