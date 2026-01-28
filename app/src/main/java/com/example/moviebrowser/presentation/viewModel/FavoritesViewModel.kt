package com.example.moviebrowser.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebrowser.data.db.toEntity
import com.example.moviebrowser.data.db.toMovie
import com.example.moviebrowser.data.repository.FavoriteRepository
import com.example.moviebrowser.domain.model.Movie
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: FavoriteRepository
) : ViewModel() {

    val favorites: StateFlow<List<Movie>> =
        repository.getAllFavorites()
            .map { list -> list.map { it.toMovie() } }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _favoritesState = MutableStateFlow(FavoritesState())
    val favoritesState: StateFlow<FavoritesState> = _favoritesState

    fun addToFavorites(movie: Movie) {
        viewModelScope.launch {
            repository.addToFavorites(movie.toEntity())
        }
    }

    fun removeFromFavorites(id: Int) {
        viewModelScope.launch {
            repository.removeFromFavorites(id)
        }
    }
}

data class FavoritesState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)