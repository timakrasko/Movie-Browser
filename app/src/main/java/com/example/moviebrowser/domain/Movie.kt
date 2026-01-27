package com.example.moviebrowser.domain

data class Movie(
    val id: String,
    val title: String,
    val posterUrl: String,
    val overview: String,
    val releaseYear: String,
    val isFavorite: Boolean = false
)