package com.example.moviebrowser.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviebrowser.presentation.viewModel.MovieDetailsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieDetailsScreen(
    movieId: Int,
) {
    val viewModel: MovieDetailsViewModel = koinViewModel(parameters = { parametersOf(movieId) })
    val state by viewModel.movieDetailsState.collectAsState()

    if (state.isLoading) {
        CircularProgressIndicator()
    } else if (state.movie != null) {
        val movie = state.movie
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = movie!!.title, style = MaterialTheme.typography.titleLarge)
            AsyncImage(model = movie.posterUrl, contentDescription = movie.title)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Year: ${movie.releaseYear}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = movie.overview)
        }
    } else {
        Text("Movie not found")
    }
}