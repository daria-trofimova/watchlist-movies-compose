package com.watchlist.movies.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

//val topLevelRoutes = listOf(
//    TopLevelRoute("Profile", Profile, Icons.Profile),
//    TopLevelRoute("Friends", Friends, Icons.Friends)
//)