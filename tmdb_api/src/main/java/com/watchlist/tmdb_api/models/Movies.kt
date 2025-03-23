package com.watchlist.tmdb_api.models

import com.google.gson.annotations.SerializedName

class Movies(
    @SerializedName("results") val results: List<Movie>,
)