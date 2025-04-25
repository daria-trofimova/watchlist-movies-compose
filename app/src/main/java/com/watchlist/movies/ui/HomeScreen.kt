package com.watchlist.movies.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.watchlist.movies.R

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsState()
    when (val currentState = state) {
        is State.Initial -> MoviesEmpty(modifier)
        is State.Loading -> MoviesLoading(modifier)
        is State.Error -> MoviesError(modifier)
        is State.Success -> Movies(currentState.movies, modifier)
    }
}

@Composable
@Preview
internal fun MoviesEmpty(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        Text(stringResource(R.string.no_movies))
    }
}

@Composable
@Preview
internal fun MoviesLoading(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}

@Composable
internal fun MoviesError(modifier: Modifier = Modifier) {
    TODO()
}

@Preview
@Composable
internal fun Movies(
    @PreviewParameter(MoviesPreviewProvider::class) movies: List<Movie>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = modifier,
    ) {
        items(movies) { movie ->
            key(movie.id) {
                Movie(movie)
            }
        }
    }
}

@Composable
internal fun Movie(movie: Movie, modifier: Modifier = Modifier) {
    Surface(
        shadowElevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .height(170.dp),
    ) { }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(4.dp)
    ) {
        MoviePoster(movie.posterLink, modifier = Modifier
            .fillMaxWidth()
            .height(100.dp))
        Text(text = movie.title)
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = movie.ratingFormatted)
    }
}

@Preview
@Composable
internal fun MoviePreview() {
    Movie(
        Movie(
            id = 100,
            title = "Titanic",
            overview = "",
            rating = 1.0f,
            posterLink = "",
            isFavorite = false
        )
    )
}