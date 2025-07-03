package com.watchlist.tmdb

import com.watchlist.tmdb.model.Movies
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * [Api documentation](https://developer.themoviedb.org/reference/intro/getting-started)
 * */
internal interface TmdbApi {

    /**
     * [Route details](https://developer.themoviedb.org/reference/movie-popular-list)
     * */
    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<Movies>
}

internal fun TmdbApi(
    baseUrl: String,
    apiKey: String,
): TmdbApi {
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
        .addInterceptor(loggingInterceptor) // TODO: disable for release build
        .build()
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(TmdbApi::class.java)
}