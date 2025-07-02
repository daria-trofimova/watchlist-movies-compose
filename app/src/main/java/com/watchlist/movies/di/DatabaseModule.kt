package com.watchlist.movies.di

import android.app.Application
import com.watchlist.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public object DatabaseModule {
    @Provides
    @Singleton
    public fun provideMoviesDatabase(
        application: Application,
    ): MoviesDatabase = MoviesDatabase(application)
}