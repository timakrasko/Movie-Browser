package com.example.moviebrowser.di

import com.example.moviebrowser.data.api.TmdbApiService
import com.example.moviebrowser.data.repository.MovieRepository
import com.example.moviebrowser.data.repository.MovieRepositoryImpl
import com.example.moviebrowser.presentation.viewModel.MovieDetailsViewModel
import com.example.moviebrowser.presentation.viewModel.MovieListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmdbApiService::class.java)
    }
    single<MovieRepository> { MovieRepositoryImpl(get(), "b493492000673e32c655001342ab405e") }
    viewModel { MovieListViewModel(get()) }
    viewModel { (movieId: Int) -> MovieDetailsViewModel(get(), movieId) }
}