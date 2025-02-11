package com.watchlist.movies.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TmdbApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MoviesResponse>

    @POST("account/$accountId/favorite")
    suspend fun setFavoriteMovie(@Body body: SetFavoriteRequestBody): Response<Any>

    @GET("account/$accountId/favorite/movies")
    suspend fun getFavoriteMovies(): Response<MoviesResponse>
}

private const val accountId = 21805174