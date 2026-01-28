package com.example.moviebrowser.data.dto

import com.example.moviebrowser.domain.model.Movie
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int,
    val title: String?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?
)

fun MovieDto.toDomain(): Movie = Movie(
    id = id,
    title = title ?: "No title",
    posterUrl = poster_path?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
    overview = overview ?: "",
    releaseYear = release_date?.take(4) ?: "Unknown"
)