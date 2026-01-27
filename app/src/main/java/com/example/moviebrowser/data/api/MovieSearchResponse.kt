package com.example.moviebrowser.data.api

import com.example.moviebrowser.data.dto.MovieDto
import kotlinx.serialization.Serializable

@Serializable
data class MovieSearchResponse(
    val Search: List<MovieDto>? = null,
    val totalResults: String? = null,
    val Response: String? = null,
    val Error: String? = null
)