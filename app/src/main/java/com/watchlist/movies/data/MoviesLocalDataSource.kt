package com.watchlist.movies.data

import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(private val dao: MovieDao) {

}