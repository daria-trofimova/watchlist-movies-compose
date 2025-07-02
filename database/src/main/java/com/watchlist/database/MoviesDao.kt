package com.watchlist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
public interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public suspend fun insertAll(
        movies: List<Movie>,
    )

    @Query("SELECT * FROM movie")
    public fun loadAll(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    public fun loadMovie(
        id: Long,
    ): Flow<Movie>
}