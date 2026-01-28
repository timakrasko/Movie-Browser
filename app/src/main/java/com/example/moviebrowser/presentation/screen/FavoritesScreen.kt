package com.example.moviebrowser.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.moviebrowser.domain.model.Movie
import com.example.moviebrowser.presentation.viewModel.FavoritesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesScreen(
    onMovieClick: (Movie) -> Unit,
) {
    val viewModel: FavoritesViewModel = koinViewModel()
    val state by viewModel.favoritesState.collectAsState()
    val favorites by viewModel.favorites.collectAsState()

    when {
        state.isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        state.errorMessage != null -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${state.errorMessage}")
            }
        }
        favorites.isEmpty() -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Улюблених фільмів немає")
            }
        }
        else -> {
            LazyColumn {
                items(favorites) { movie ->
                    MovieCard(
                        movie = movie,
                        isFavorite = true,
                        onClick = { onMovieClick(movie) },
                        onFavoriteClick = { viewModel.removeFromFavorites(movie.id) }
                    )
                }
            }
        }
    }
}