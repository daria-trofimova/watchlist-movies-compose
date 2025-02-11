package com.watchlist.movies.data

import com.google.gson.annotations.SerializedName

class SetFavoriteRequestBody(
    @SerializedName("media_type") val mediaType: String,
    @SerializedName("media_id") val mediaId: Long,
    @SerializedName("favorite") val isFavorite: Boolean,
)