package com.watchlist.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1, exportSchema = false)
internal abstract class MoviesRoomDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
}

internal fun MoviesRoomDatabase(application: Application): MoviesRoomDatabase {
    return Room.databaseBuilder(
        application.applicationContext,
        MoviesRoomDatabase::class.java, "watchlist-movies-database"
    ).build()
}

