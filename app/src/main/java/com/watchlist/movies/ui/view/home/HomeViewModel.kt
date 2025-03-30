package com.watchlist.movies.ui.view.home

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
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesStreamUseCase: GetMoviesStreamUseCase,
    private val fetchMoviesUseCase: FetchMoviesUseCase,
) : ViewModel() {

    private val isLoading = MutableStateFlow(true)

    private val errorMessage = MutableStateFlow<String?>(null)

    private val movies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())

    val uiState: StateFlow<HomeUiState> = combine(
        movies,
        isLoading,
        errorMessage
    ) { movies, isLoading, errorMessage ->
        HomeUiState(
            movies = movies,
            isLoading = isLoading,
            errorMessage = errorMessage
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState()
    )

    init {
        viewModelScope.launch {
            getMoviesStreamUseCase().collect {
                movies.emit((it.map { Movie.from(it) }))
            }
        }
        viewModelScope.launch {
            try {
                fetchMoviesUseCase()
            } catch (exception: Exception) {
                errorMessage.emit(getErrorMessage(exception))
            } finally {
                isLoading.emit(false)
            }
        }
    }

    fun markErrorMessageAsSeen() {
        viewModelScope.launch {
            errorMessage.emit(null)
        }
    }

    private fun getErrorMessage(exception: Exception): String = exception.message ?: ""
}

data class HomeUiState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
