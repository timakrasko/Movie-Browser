package com.example.moviebrowser.data.api

import com.example.moviebrowser.data.dto.MovieDto

data class MovieListResponse(
    val results: List<MovieDto>
)