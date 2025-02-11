package com.watchlist.movies

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
                .header("Authorization", "Bearer $apiKey")
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

// TODO: move to a better place
private const val baseUrl = "https://api.themoviedb.org/3/"
private const val apiKey =
    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYjRlNWM5YmEzMjhiMjQxZGVjYzdjYWQ0ZTZhNzlkMyIsIm5iZiI6MTczOTA0MzI4Mi4wNDksInN1YiI6IjY3YTdiMWQyMTcyOWZmYTFjYWUwNzU1YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.JjVKqap1HPFn37sZHy5m4Qw7T1UFq3zcz2jzcS3B7G8"