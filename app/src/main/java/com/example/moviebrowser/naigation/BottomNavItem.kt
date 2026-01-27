package com.example.moviebrowser.naigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem (
    val icon: ImageVector,
    val title: String
)

val TOP_LEVEL_DESTINATION = mapOf(
    Route.MovieList to BottomNavItem(
        icon = Icons.Outlined.List,
        title = "Movies"
    ),

)