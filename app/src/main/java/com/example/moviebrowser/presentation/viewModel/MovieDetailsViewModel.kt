package com.example.moviebrowser.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.moviebrowser.data.repository.MovieRepository
import com.example.moviebrowser.domain.Movie

class MovieDetailsViewModel(
    private val movieId: Int,
    private val repository: MovieRepository,
) : ViewModel() {

}