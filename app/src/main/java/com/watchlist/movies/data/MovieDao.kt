package com.watchlist.movies.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MovieDao {
    @Insert
    fun insertAll(movies: List<Movie>)

    @Update
    fun update(movie: Movie)

    @Query("SELECT * FROM movie")
    fun loadAll(): List<Movie>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun loadAllFavorite(): List<Movie>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun loadMovie(id: String): Movie
}