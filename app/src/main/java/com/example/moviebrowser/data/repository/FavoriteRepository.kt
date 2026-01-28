package com.example.moviebrowser.data.repository

import com.example.moviebrowser.data.db.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAllFavorites(): Flow<List<FavoriteMovieEntity>>
    suspend fun addToFavorites(movie: FavoriteMovieEntity)
    suspend fun removeFromFavorites(id: Int)
}