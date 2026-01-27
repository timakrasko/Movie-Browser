package com.example.moviebrowser.data.repository

import com.example.moviebrowser.data.api.MovieApiService
import com.example.moviebrowser.domain.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val api: MovieApiService,
    private val apiKey: String
) : MovieRepository {

    override fun getMovies(): Flow<List<Movie>> = flow {
        val response = api.getPopularMovies(apiKey)
        val movies = response.results.map { it }
        emit(movies)
    }

    override fun getMovieById(id: Int): Flow<Movie?> = flow {
        val response = api.getPopularMovies(apiKey)
        val movie = response.results.find { it.id == id }
        emit(movie)
    }
}