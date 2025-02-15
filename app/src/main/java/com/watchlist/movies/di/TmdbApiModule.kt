package com.watchlist.movies.di

import com.watchlist.movies.BuildConfig
import com.watchlist.movies.data.TmdbApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TmdbApiModule {

    @Provides
    @Singleton
    fun provideTmdbApi(): TmdbApi {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
            val request: Request = chain.request()
                .newBuilder()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer ${BuildConfig.TMDB_API_KEY}")
                .build()
            chain.proceed(request)
        }
            .addInterceptor(loggingInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(TmdbApi::class.java)
    }
}

private const val baseUrl = "https://api.themoviedb.org/3/"