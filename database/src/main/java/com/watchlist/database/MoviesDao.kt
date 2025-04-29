package com.watchlist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(
        movies: List<Movie>,
    )

    @Query("SELECT * FROM movie")
    fun loadAll(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun loadMovie(
        id: Long,
    ): Flow<Movie>
}