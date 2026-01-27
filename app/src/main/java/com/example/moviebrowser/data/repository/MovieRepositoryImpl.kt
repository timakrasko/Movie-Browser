package com.example.moviebrowser.data.repository

import com.example.moviebrowser.data.api.OmdbApiService

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.moviebrowser.data.dto.toDomain
import com.example.moviebrowser.domain.Movie

class MovieRepositoryImpl(
    private val api: OmdbApiService,
    private val apiKey: String
) : MovieRepository {

    override fun getMovies(): Flow<List<Movie>> = flow {
        val response = api.searchMovies(apiKey, "star")
        val movies = response.Search?.map { it.toDomain() } ?: emptyList()
        emit(movies)
    }

    override fun getMovieById(id: String): Flow<Movie?> = flow {
        val movieDto = api.getMovieDetails(apiKey, id)
        emit(movieDto.toDomain())
    }
}