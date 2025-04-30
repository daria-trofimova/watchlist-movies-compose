package com.watchlist.movies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.watchlist.feature.home.HomeScreen
import com.watchlist.feature.home.HomeViewModel
import com.watchlist.movies.ui.navigation.Screen
import com.watchlist.movies.ui.theme.MoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        MoviesTopAppBar(
                            canNavigateBack = navController.previousBackStackEntry != null,
                            navigateUp = { navController.navigateUp() }
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
                            val viewModel = hiltViewModel<HomeViewModel>()
                            HomeScreen(viewModel = viewModel)
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