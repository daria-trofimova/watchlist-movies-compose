package com.watchlist.feature.moviedetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.watchlist.core.ui.MoviePoster
import com.watchlist.core.ui.MoviePosterFullscreen
import com.watchlist.data.movies.model.Movie

@Composable
fun MovieDetailsScreen(movieId: Long, modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<MovieDetailsViewModel, MovieDetailsViewModel.Factory> { factory ->
        factory.create(movieId = movieId)
    }
    val state = viewModel.state.collectAsState()
    val currentState = state.value
    if (currentState is MovieDetailUiState.Success) {
        Movie(currentState.movie, modifier)
    }
}

@Composable
fun Movie(movie: Movie, modifier: Modifier = Modifier) {
    val isFullScreen = remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        MoviePoster(
            movie.posterLink,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isFullScreen.value = true },
        )
        Text(text = movie.overview, modifier = Modifier.padding(8.dp))
    }
    if (isFullScreen.value) {
        MoviePosterFullscreen(movie.posterLink)
    }
}

@Preview
@Composable
fun MoviePreview() {
    Movie(
        Movie(
            id = 100,
            title = "Titanic",
            overview = "", rating = 3.2f,
            posterLink = "",
        )
    )
}