package com.watchlist.tmdb_api.model

import com.google.gson.annotations.SerializedName

internal class Movies(
    @SerializedName("results") val results: List<Movie>,
)