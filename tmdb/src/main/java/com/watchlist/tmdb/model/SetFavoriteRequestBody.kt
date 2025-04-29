package com.watchlist.tmdb.model

import com.google.gson.annotations.SerializedName

internal class SetFavoriteRequestBody(
    @SerializedName("media_type") val mediaType: MediaType,
    @SerializedName("media_id") val mediaId: Long,
    @SerializedName("favorite") val isFavorite: Boolean,
)