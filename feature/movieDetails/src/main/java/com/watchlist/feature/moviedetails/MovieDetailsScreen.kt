package com.watchlist.feature.moviedetails

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.watchlist.core.ui.MoviePoster
import com.watchlist.core.ui.MoviePosterFullscreen
import com.watchlist.feature.moviedetails.model.MovieDetails

@Composable
public fun MovieDetailsScreen(movieId: Long, modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<MovieDetailsViewModel, MovieDetailsViewModel.Factory> { factory ->
        factory.create(movieId = movieId)
    }
    val state = viewModel.state.collectAsState()
    val currentState = state.value
    when (currentState) {
        is MovieDetailsUiState.Initial -> MovieDetailsLoading(modifier)
        is MovieDetailsUiState.Success -> MovieDetails(currentState.movieDetails, modifier)
        is MovieDetailsUiState.Error -> MovieDetailsError(currentState.error)
    }
    if (currentState is MovieDetailsUiState.Success) {
        MovieDetails(currentState.movieDetails, modifier)
    }
}

@Composable
@Preview
internal fun MovieDetailsLoading(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}

@Composable
internal fun MovieDetails(movieDetails: MovieDetails, modifier: Modifier = Modifier) {
    val isFullScreen = remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        MoviePoster(
            movieDetails.poster.standardLink,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isFullScreen.value = true },
        )
        Text(text = movieDetails.overview, modifier = Modifier.padding(8.dp))
    }
    if (isFullScreen.value) {
        MoviePosterFullscreen(
            movieDetails.poster.standardLink,
            onDismiss = { isFullScreen.value = false })
    }
}

@Composable
internal fun MovieDetailsError(error: Throwable) {
    val text = error.localizedMessage
    val toast = Toast.makeText(LocalContext.current, text, Toast.LENGTH_SHORT)
    toast.show()
}

@Preview
@Composable
internal fun MoviePreview() {
    MovieDetails(
        MovieDetails(
            id = 100,
            title = "Titanic",
            overview = "", rating = 3.2f,
            poster = MovieDetails.Poster(""),
        )
    )
}