package com.watchlist.feature.moviedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.watchlist.core.ui.MoviePoster

@Composable
fun MovieDetailsScreen(movieId: Long, modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<MovieDetailsViewModel, MovieDetailsViewModel.Factory> { factory ->
        factory.create(movieId = movieId)
    }
    val state = viewModel.state.collectAsState()
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        val currentState = state.value
        if (currentState is MovieDetailUiState.Success) {
            MoviePoster(currentState.movie.posterLink)
        }
        Text(
            text = "Movie Details. Movie id: $movieId",
            modifier = modifier
        )
    }
}