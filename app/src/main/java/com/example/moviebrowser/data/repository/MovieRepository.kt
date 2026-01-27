package com.example.moviebrowser.data.repository

import com.example.moviebrowser.domain.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<List<Movie>>
    fun getMovieById(id: Int): Flow<Movie?>
}