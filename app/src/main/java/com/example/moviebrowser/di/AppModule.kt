package com.example.moviebrowser.di

import com.example.moviebrowser.data.api.MovieApiService
import com.example.moviebrowser.data.repository.MovieRepository
import com.example.moviebrowser.data.repository.MovieRepositoryImpl
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
            .create(MovieApiService::class.java)
    }
    single<MovieRepository> { MovieRepositoryImpl(get(), "YOUR_API_KEY") }
    viewModel { MovieListViewModel(get()) }
}