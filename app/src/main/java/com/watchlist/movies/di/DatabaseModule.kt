package com.watchlist.movies.di

import android.app.Application
import com.watchlist.database.MoviesDao
import com.watchlist.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
    ): MoviesDatabase = MoviesDatabase(application)

    @Provides
    fun provideMoviesDao(
        moviesDatabase: MoviesDatabase,
    ): MoviesDao = moviesDatabase.moviesDao()
}