package com.watchlist.movies.ui.view

import android.widget.ImageView
import coil3.ImageLoader
import coil3.imageLoader
import coil3.load
import coil3.request.Disposable
import coil3.request.ImageRequest

inline fun ImageView.loadPoster(
    posterPath: String,
    imageLoader: ImageLoader = context.imageLoader,
    builder: ImageRequest.Builder.() -> Unit = {},
): Disposable = this.load(imagesBaseUrl + posterPath, imageLoader, builder)

const val imagesBaseUrl = "http://image.tmdb.org/t/p/w500"