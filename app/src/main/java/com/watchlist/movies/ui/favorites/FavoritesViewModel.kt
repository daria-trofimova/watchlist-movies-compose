package com.watchlist.movies.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watchlist.movies.domain.FetchMoviesUseCase
import com.watchlist.movies.domain.GetMoviesStreamUseCase
import com.watchlist.movies.ui.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getMoviesStreamUseCase: GetMoviesStreamUseCase,
    private val fetchMoviesUseCase: FetchMoviesUseCase,
) : ViewModel() {

    private val isLoading = MutableStateFlow(true)

    private val errorMessage = MutableStateFlow<String?>(null)

    private val movies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())

    val uiState: StateFlow<FavoritesUiState> = combine(
        movies,
        isLoading,
        errorMessage
    ) { movies, isLoading, errorMessage ->
        FavoritesUiState(
            movies = movies,
            isLoading = isLoading,
            errorMessage = errorMessage
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FavoritesUiState()
    )

    init {
        viewModelScope.launch {
            getMoviesStreamUseCase(onlyFavorite = true).collect {
                movies.emit(it.map { Movie.from(it) })
            }
        }
        viewModelScope.launch {
            try {
                fetchMoviesUseCase(onlyFavorite = true)
            } catch (exception: IOException) {
                // TODO
            }
        }
    }
}

data class FavoritesUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)