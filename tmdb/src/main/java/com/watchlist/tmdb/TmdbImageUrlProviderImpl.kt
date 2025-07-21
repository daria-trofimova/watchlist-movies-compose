package com.watchlist.tmdb

import com.watchlist.tmdb.model.TmdbImageSize

/**
 *  [Api documentation](https://developer.themoviedb.org/docs/image-basics)
 * */
internal class TmdbImageUrlProviderImpl(
    private val imageBaseUrl: String,
) : TmdbImageUrlProvider {

    override fun invoke(imagePath: String, size: TmdbImageSize): String =
        "$imageBaseUrl${size.value}$imagePath"
}