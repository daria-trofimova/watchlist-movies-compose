package com.watchlist.movies.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.watchlist.movies.ui.navigation.topLevelScreens

@Composable
@Preview
internal fun MoviesNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavigationBar(modifier = modifier.fillMaxWidth()) {
        topLevelScreens.forEach { screen ->
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            NavigationBarItem(selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route)
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                }, icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = "",
                    )
                }, label = { Text(text = stringResource(screen.label)) })

        }
    }
}