package com.example.moviebrowser.data.repository

import com.example.moviebrowser.data.db.FavoriteMovieDao
import com.example.moviebrowser.data.db.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

class FavoriteRepositoryImpl(private val dao: FavoriteMovieDao): FavoriteRepository {
    override fun getAllFavorites(): Flow<List<FavoriteMovieEntity>> = dao.getAll()

    override suspend fun addToFavorites(movie: FavoriteMovieEntity) = dao.insert(movie)

    override suspend fun removeFromFavorites(id: Int) = dao.deleteById(id)

}