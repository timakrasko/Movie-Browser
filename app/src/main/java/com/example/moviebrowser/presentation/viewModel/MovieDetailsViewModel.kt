package com.example.moviebrowser.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebrowser.data.repository.MovieRepository
import com.example.moviebrowser.domain.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: MovieRepository,
    private val movieId: Int
) : ViewModel() {

    private val _movieDetailsState = MutableStateFlow(MovieDetailsState())
    val movieDetailsState: StateFlow<MovieDetailsState> = _movieDetailsState

    init {
        loadMovieDetails()
    }

    private fun loadMovieDetails() {
        viewModelScope.launch {
            _movieDetailsState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                repository.getMovieById(movieId).collect { movie ->
                    _movieDetailsState.update {
                        it.copy(
                            isLoading = false,
                            movie = movie,
                            errorMessage = null
                        )
                    }
                }
            } catch (e: Exception) {
                _movieDetailsState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message,
                        movie = null
                    )
                }
            }
        }
    }
}

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val movie: Movie? = null
)