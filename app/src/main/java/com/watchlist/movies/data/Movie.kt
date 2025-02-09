package com.watchlist.movies.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey val id: String,
    val title: String,
    val rating: Float,
    val posterLink: String,
    var isFavorite: Boolean,
)