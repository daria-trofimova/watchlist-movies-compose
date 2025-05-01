package com.watchlist.feature.moviedetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MovieDetailsScreen(movieId: Long, modifier: Modifier = Modifier) {
    Text(
        text = "Movie Details. Movie id: $movieId",
        modifier = modifier
    )

}