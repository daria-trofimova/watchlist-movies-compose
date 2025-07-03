package com.watchlist.tmdb

public interface TmdbImageUrlProvider {
    public operator fun invoke(imagePath: String, size: TmdbImageSize): String
}

public fun TmdbImageUrlProvider(imageBaseUrl: String): TmdbImageUrlProvider =
    TmdbImageUrlProviderImpl(imageBaseUrl)