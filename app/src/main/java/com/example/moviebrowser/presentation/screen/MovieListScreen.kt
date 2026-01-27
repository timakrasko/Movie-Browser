package com.example.moviebrowser.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviebrowser.domain.Movie
import com.example.moviebrowser.presentation.viewModel.MovieListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieListScreen(
    onMovieClick: (Movie) -> Unit
) {
    val viewModel: MovieListViewModel = koinViewModel()
    val movies by viewModel.movies.collectAsState()

    LazyColumn {
        items(movies) { movie ->
            MovieCard(movie = movie, onClick = { onMovieClick(movie) })
        }
    }
}

@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = movie.title,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
                Text(text = movie.overview, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}