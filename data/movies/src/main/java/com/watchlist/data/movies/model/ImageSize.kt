package com.watchlist.data.movies.model

import com.watchlist.tmdb.TmdbImageSize

public enum class ImageSize {
    PREVIEW,
    STANDARD,
    HIGH_RES
}

internal fun ImageSize.toTmdbSize(): TmdbImageSize = when (this) {
    ImageSize.PREVIEW -> TmdbImageSize.W300
    ImageSize.STANDARD -> TmdbImageSize.W500
    ImageSize.HIGH_RES -> TmdbImageSize.W780
}