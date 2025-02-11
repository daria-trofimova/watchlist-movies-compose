package com.watchlist.movies.data

import com.google.gson.annotations.SerializedName

class MoviesResponse(@SerializedName("results") val results: List<MovieResponse>)