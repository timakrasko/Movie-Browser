package com.example.moviebrowser.data.repository

import com.example.moviebrowser.data.api.TmdbApiService

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.moviebrowser.data.dto.toDomain
import com.example.moviebrowser.domain.Movie

class MovieRepositoryImpl(
    private val api: TmdbApiService,
    private val apiKey: String
) : MovieRepository {

    override fun getMovies(): Flow<List<Movie>> = flow {
        val moviesResponse = api.getPopularMovies(apiKey)
        val movies = moviesResponse.results.map { it.toDomain() }
        emit(movies)
    }

    override fun getMovieById(id: Int): Flow<Movie?> = flow {
        val moviesResponse = api.getPopularMovies(apiKey)
        val movie = moviesResponse.results.find { it.id == id }?.toDomain()
        emit(movie)
    }
}