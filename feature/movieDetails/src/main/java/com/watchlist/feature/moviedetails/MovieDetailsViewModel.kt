package com.watchlist.feature.moviedetails

import androidx.lifecycle.ViewModel
import com.watchlist.data.movies.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {
}