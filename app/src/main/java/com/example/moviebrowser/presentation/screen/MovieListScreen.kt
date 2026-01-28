package com.example.moviebrowser.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.moviebrowser.domain.model.Movie
import com.example.moviebrowser.presentation.viewModel.FavoritesViewModel
import com.example.moviebrowser.presentation.viewModel.MovieListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieListScreen(
    onMovieClick: (Movie) -> Unit
) {
    val viewModel: MovieListViewModel = koinViewModel()
    val state by viewModel.movieListState.collectAsState()
    val favoritesViewModel: FavoritesViewModel = koinViewModel()
    val favorites by favoritesViewModel.favorites.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = searchQuery,
            onValueChange = { viewModel.onSearchQueryChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            singleLine = true,
        )
        when {
            state.isLoading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(48.dp))
            }

            state.errorMessage != null -> Text("Error: ${state.errorMessage}")
            else -> {
                LazyColumn {
                    items(state.movies) { movie ->
                        val isFavorite = favorites.any { it.id == movie.id }
                        MovieCard(
                            movie = movie,
                            isFavorite = isFavorite,
                            onClick = { onMovieClick(movie) },
                            onFavoriteClick = {
                                if (isFavorite) {
                                    favoritesViewModel.removeFromFavorites(movie.id)
                                } else {
                                    favoritesViewModel.addToFavorites(movie)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MovieCard(
    movie: Movie,
    isFavorite: Boolean,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = movie.overview,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isFavorite) "Remove from favorite" else "Add to favorite"
                )
            }
        }
    }
}