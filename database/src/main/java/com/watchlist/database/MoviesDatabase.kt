package com.watchlist.database

import android.app.Application

class MoviesDatabase internal constructor(private val database: MoviesRoomDatabase) {
    fun moviesDao(): MoviesDao = database.moviesDao()
}

fun MoviesDatabase(application: Application): MoviesDatabase {
    val roomDatabase = MoviesRoomDatabase(application)
    return MoviesDatabase(roomDatabase)
}