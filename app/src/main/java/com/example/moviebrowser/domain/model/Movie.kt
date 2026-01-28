package com.example.moviebrowser.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val overview: String,
    val releaseYear: String,
    val isFavorite: Boolean = false
)