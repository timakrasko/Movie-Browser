package com.example.moviebrowser.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebrowser.data.repository.MovieRepository
import com.example.moviebrowser.domain.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movieListState = MutableStateFlow(MovieListState())
    val movieListState: StateFlow<MovieListState> = _movieListState

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _movieListState.value = _movieListState.value.copy(isLoading = true, errorMessage = null)
            try {
                repository.getMovies().collectLatest { movieList ->
                    _movieListState.value = MovieListState(
                        isLoading = false,
                        errorMessage = null,
                        movies = movieList
                    )
                }
            } catch (e: Exception) {
                _movieListState.value = MovieListState(
                    isLoading = false,
                    errorMessage = e.message,
                    movies = emptyList()
                )
            }
        }
    }
}

data class MovieListState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val movies: List<Movie> = emptyList()
)