package com.example.moviebrowser.domain

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val overview: String,
    val releaseYear: String,
    val isFavorite: Boolean = false
)