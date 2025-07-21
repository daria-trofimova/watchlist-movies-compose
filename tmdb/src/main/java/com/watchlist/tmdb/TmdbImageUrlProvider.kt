package com.watchlist.tmdb

import com.watchlist.tmdb.model.TmdbImageSize

public interface TmdbImageUrlProvider {
    public operator fun invoke(imagePath: String, size: TmdbImageSize): String
}

public fun TmdbImageUrlProvider(imageBaseUrl: String): TmdbImageUrlProvider =
    TmdbImageUrlProviderImpl(imageBaseUrl)