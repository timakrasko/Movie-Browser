package com.example.moviebrowser.naigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route: NavKey {
    @Serializable
    data object MovieList: Route

    @Serializable
    data class MovieDetails(val id: String): Route

    @Serializable
    data object Favorite: Route
}