package com.watchlist.movies.data

import com.watchlist.movies.BuildConfig
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TmdbApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MoviesResponse>

    @POST("account/${BuildConfig.TMDB_ACCOUNT_ID}/favorite")
    suspend fun setFavoriteMovie(@Body body: SetFavoriteRequestBody): Response<Any>

    @GET("account/${BuildConfig.TMDB_ACCOUNT_ID}/favorite/movies")
    suspend fun getFavoriteMovies(): Response<MoviesResponse>
}