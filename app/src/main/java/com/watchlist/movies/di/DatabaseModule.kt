package com.watchlist.movies.di

import android.app.Application
import androidx.room.Room
import com.watchlist.movies.data.AppDatabase
import com.watchlist.movies.data.MoviesDao
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
    fun provideAppDatabase(application: Application): AppDatabase = Room.databaseBuilder(
        application.applicationContext,
        AppDatabase::class.java, "watchlist-movies-database"
    ).build()

    @Provides
    fun provideMoviesDao(appDatabase: AppDatabase): MoviesDao = appDatabase.moviesDao()
}