package com.watchlist.movies.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Query("UPDATE movie SET isFavorite=:isFavorite WHERE id = :id")
    fun updateFavorite(id: Long, isFavorite: Boolean)

    @Query("SELECT * FROM movie")
    fun loadAll(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun loadAllFavorite(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun loadMovie(id: Long): Flow<Movie>
}