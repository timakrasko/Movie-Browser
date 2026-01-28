package com.example.moviebrowser.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.moviebrowser.domain.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class FavoritesViewModel : ViewModel() {

    private val _favoritesState = MutableStateFlow(FavoritesState())
    val favoritesState: StateFlow<FavoritesState> = _favoritesState

    fun addToFavorites(movie: Movie) {
        _favoritesState.update { state ->
            if (state.favorites.none { it.id == movie.id }) {
                state.copy(favorites = state.favorites + movie.copy(isFavorite = true))
            } else state
        }
    }

    fun removeFromFavorites(movieId: Int) {
        _favoritesState.update { state ->
            state.copy(favorites = state.favorites.filterNot { it.id == movieId })
        }
    }

    fun clearError() {
        _favoritesState.update { it.copy(errorMessage = null) }
    }
}

data class FavoritesState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val favorites: List<Movie> = emptyList()
)