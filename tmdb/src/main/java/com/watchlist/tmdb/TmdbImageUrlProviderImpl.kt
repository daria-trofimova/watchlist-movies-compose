package com.watchlist.tmdb

/**
 *  [Api documentation](https://developer.themoviedb.org/docs/image-basics)
 * */
internal class TmdbImageUrlProviderImpl(
    private val imageBaseUrl: String,
) : TmdbImageUrlProvider {

    override fun invoke(imagePath: String, size: TmdbImageSize): String =
        "$imageBaseUrl${size.value}$imagePath"
}