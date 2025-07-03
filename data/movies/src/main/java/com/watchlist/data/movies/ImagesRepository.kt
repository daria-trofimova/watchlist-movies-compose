package com.watchlist.data.movies

import com.watchlist.data.movies.model.Image
import com.watchlist.data.movies.model.ImageSize
import com.watchlist.data.movies.model.toTmdbSize
import com.watchlist.tmdb.TmdbImageUrlProvider
import jakarta.inject.Inject

public class ImagesRepository @Inject constructor(
    private val tmdbImageUrlProvider: TmdbImageUrlProvider,
) {
    public fun getImage(posterLink: String, imageSize: ImageSize): Image =
        Image(link = tmdbImageUrlProvider(posterLink, imageSize.toTmdbSize()), size = imageSize)
}