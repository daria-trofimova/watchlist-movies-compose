package com.watchlist.database

import android.app.Application

public class MoviesDatabase internal constructor(private val database: MoviesRoomDatabase) {
    public fun moviesDao(): MoviesDao = database.moviesDao()
}

public fun MoviesDatabase(application: Application): MoviesDatabase {
    val roomDatabase = MoviesRoomDatabase(application)
    return MoviesDatabase(roomDatabase)
}