package com.watchlist.movies.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.watchlist.core.ui.BarsVisibilityController
import com.watchlist.core.ui.LocalBarsVisibilityController
import com.watchlist.feature.favorites.FavoritesScreen
import com.watchlist.feature.home.HomeScreen
import com.watchlist.feature.moviedetails.MovieDetailsScreen
import com.watchlist.movies.ui.navigation.Screen

@Composable
internal fun MoviesApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route?.let { route ->
        Screen.from(route, backStackEntry?.arguments)
    } ?: Screen.Home
    val screenTitle = currentScreen.title
    val isNavigationBarScreen = currentScreen is Screen.NavigationBarScreen
    val canNavigateBack = !isNavigationBarScreen && navController.previousBackStackEntry != null
    val barsVisibilityController = remember { BarsVisibilityController() }
    CompositionLocalProvider(LocalBarsVisibilityController provides barsVisibilityController) {
        Scaffold(
            topBar = {
                AnimatedVisibility(
                    visible = barsVisibilityController.topBar.isVisible,
                    enter = slideInVertically { -it },
                    exit = slideOutVertically { -it },
                ) {
                    MoviesTopAppBar(
                        canNavigateBack = canNavigateBack,
                        navigateUp = { navController.navigateUp() },
                        title = screenTitle.asString()
                    )
                }
            },
            bottomBar = {
                AnimatedVisibility(
                    visible = barsVisibilityController.bottomBar.isVisible,
                    enter = slideInVertically { it },
                    exit = slideOutVertically { it }
                ) {
                    MoviesNavigationBar(navController = navController)
                }
            },

            ) { padding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(padding),
            ) {
                composable(Screen.Home.route) {
                    HomeScreen(onMovieClick = { movie ->
                        navController.navigate(
                            Screen.MovieDetails.createRoute(
                                id = movie.id,
                                title = movie.title
                            )
                        )
                    })
                }
                composable(Screen.Favorites.route) {
                    FavoritesScreen()
                }
                composable(
                    Screen.MovieDetails.ROUTE_PATTERN,
                    arguments = listOf(navArgument(Screen.MovieDetails.ID_ARGUMENT_KEY) {
                        type = NavType.LongType
                    },
                        navArgument(Screen.MovieDetails.TITLE_ARGUMENT_KEY) {
                            type = NavType.StringType
                        })
                ) { backStackEntry ->
                    val movieId =
                        backStackEntry.arguments?.getLong(Screen.MovieDetails.ID_ARGUMENT_KEY)
                            ?: throw IllegalStateException("Missing required argument: ${Screen.MovieDetails.ID_ARGUMENT_KEY}")
                    MovieDetailsScreen(movieId = movieId)
                }
            }
        }
    }
}