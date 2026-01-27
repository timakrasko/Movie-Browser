package com.example.moviebrowser.data.api

import com.example.moviebrowser.domain.Movie

data class MovieListResponse(
    val results: List<Movie>
)