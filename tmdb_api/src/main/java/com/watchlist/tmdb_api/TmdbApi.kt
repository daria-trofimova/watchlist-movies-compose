package com.watchlist.tmdb_api

import TMDB_ACCOUNT_ID
import TMDB_API_KEY
import com.watchlist.tmdb_api.model.Movies
import com.watchlist.tmdb_api.model.SetFavoriteRequestBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * [Api documentation](https://developer.themoviedb.org/reference/intro/getting-started)
 * */
internal interface TmdbApi {

    /**
     * [Route details](https://developer.themoviedb.org/reference/movie-popular-list)
     * */
    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<Movies>

    /**
     * [Route details](https://developer.themoviedb.org/reference/account-add-favorite)
     * */
    @POST("account/$TMDB_ACCOUNT_ID/favorite")
    suspend fun setFavoriteMovie(
        @Body body: SetFavoriteRequestBody,
    ): Response<Any>

    /**
     * [Route details](https://developer.themoviedb.org/reference/account-get-favorites)
     * */
    @GET("account/$TMDB_ACCOUNT_ID/favorite/movies")
    suspend fun getFavoriteMovies(): Response<Movies>
}

internal fun TmdbApi(
    baseUrl: String,
): TmdbApi {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request: Request = chain.request()
                .newBuilder()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer $TMDB_API_KEY")
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