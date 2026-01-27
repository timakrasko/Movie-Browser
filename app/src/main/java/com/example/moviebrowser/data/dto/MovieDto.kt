package com.example.moviebrowser.data.dto

import com.example.moviebrowser.domain.Movie
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val imdbID: String? = null,
    val Title: String? = null,
    val Year: String? = null,
    val Poster: String? = null,
    val Plot: String? = null
)

fun MovieDto.toDomain(): Movie = Movie(
    id = imdbID ?: "",
    title = Title ?: "No title",
    posterUrl = Poster ?: "",
    overview = Plot ?: "",
    releaseYear = Year ?: "Unknown"
)