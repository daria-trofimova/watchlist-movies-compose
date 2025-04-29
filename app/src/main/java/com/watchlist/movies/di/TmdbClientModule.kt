package com.watchlist.movies.di

import com.watchlist.movies.BuildConfig
import com.watchlist.tmdb.TmdbClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TmdbClientModule {

    @Provides
    @Singleton
    fun provideTmdbClient(@IoDispatcher dispatcher: CoroutineDispatcher): TmdbClient =
        TmdbClient(baseUrl = BuildConfig.TMDB_BASE_URL, dispatcher = dispatcher)
}