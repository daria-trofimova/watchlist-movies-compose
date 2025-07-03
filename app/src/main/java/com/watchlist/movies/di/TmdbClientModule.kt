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
public object TmdbClientModule {

    @Provides
    @Singleton
    public fun provideTmdbClient(@IoDispatcher dispatcher: CoroutineDispatcher): TmdbClient =
        TmdbClient(
            baseUrl = BuildConfig.TMDB_BASE_URL,
            apiKey = BuildConfig.TMDB_API_KEY,
            dispatcher = dispatcher,
        )
}