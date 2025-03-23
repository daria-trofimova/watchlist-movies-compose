package com.watchlist.movies.di

import com.watchlist.tmdb_api.TmdbApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TmdbApiModule {

    @Provides
    @Singleton
    fun provideTmdbApi(): TmdbApi = TmdbApi(baseUrl = baseUrl)
}

private const val baseUrl = "https://api.themoviedb.org/3/"