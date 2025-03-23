package com.watchlist.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
}

fun MoviesDatabase(application: Application): MoviesDatabase {
    return Room.databaseBuilder(
        application.applicationContext,
        MoviesDatabase::class.java, "watchlist-movies-database"
    ).build()
}

