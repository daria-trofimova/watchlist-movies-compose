package com.watchlist.feature.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
public fun FavoritesScreen(modifier: Modifier = Modifier) {
    Column {
        Text(text = stringResource(id = R.string.favorites))
    }
}