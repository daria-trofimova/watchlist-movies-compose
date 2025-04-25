package com.watchlist.movies.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.watchlist.movies.R

@Composable
@Preview
internal fun FavoritesScreen(modifier: Modifier = Modifier) {
    Text(text = stringResource(id = R.string.favorites))
}