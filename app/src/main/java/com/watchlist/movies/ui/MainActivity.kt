package com.watchlist.movies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.watchlist.core.ui.theme.MoviesAppTheme
import com.watchlist.feature.home.HomeScreen
import com.watchlist.feature.moviedetails.MovieDetailsScreen
import com.watchlist.movies.ui.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTheme {
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentScreen =
                    backStackEntry?.destination?.route?.let { Screen.from(it) } ?: Screen.Home
                Scaffold(
                    topBar = {
                        MoviesTopAppBar(
                            canNavigateBack = navController.previousBackStackEntry != null,
                            navigateUp = { navController.navigateUp() },
                            title = currentScreen.title
                        )
                    },
                    bottomBar = { MoviesNavigationBar(navController = navController) }
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(padding),
                    ) {
                        composable(route = Screen.Home.route) {
                            HomeScreen(onMovieClick = { movie ->
                                navController.navigate("${Screen.MovieDetails.prefix}/${movie.id}")
                            })
                        }
                        composable(
                            route = Screen.MovieDetails().route,
                            arguments = listOf(navArgument(Screen.MovieDetails.parameter) {
                                type =
                                    NavType.LongType
                            })
                        ) { backStackEntry ->
                            val movieId =
                                backStackEntry.arguments?.getLong(Screen.MovieDetails.parameter)
                                    ?: 0
                            MovieDetailsScreen(movieId = movieId)
                        }
                        composable(route = Screen.Favorites.route) {
                            FavoritesScreen()
                        }
                    }
                }
            }
        }
    }
}