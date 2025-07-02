package com.watchlist.tmdb.model

import com.google.gson.annotations.SerializedName

public class Movie(
    @SerializedName("id") public val id: Long,
    @SerializedName("title") public val title: String,
    @SerializedName("overview") public val overview: String,
    @SerializedName("vote_average") public val voteAverage: Float,
    @SerializedName("poster_path") public val posterPath: String,
)