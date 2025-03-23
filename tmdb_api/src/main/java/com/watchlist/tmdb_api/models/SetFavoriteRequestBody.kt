package com.watchlist.tmdb_api.models

import com.google.gson.annotations.SerializedName

class SetFavoriteRequestBody(
    @SerializedName("media_type") val mediaType: MediaType,
    @SerializedName("media_id") val mediaId: Long,
    @SerializedName("favorite") val isFavorite: Boolean,
)