package com.example.moviebrowser.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviebrowser.domain.model.Movie

@Entity(tableName = "favorite_movies")
data class FavoriteMovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterUrl: String,
    val overview: String,
    val releaseYear: String
)

fun Movie.toEntity() = FavoriteMovieEntity(
    id = id,
    title = title,
    posterUrl = posterUrl,
    overview = overview,
    releaseYear = releaseYear
)

fun FavoriteMovieEntity.toMovie() = Movie(
    id = id,
    title = title,
    posterUrl = posterUrl,
    overview = overview,
    releaseYear = releaseYear
)