package com.example.moviebrowser.di

import com.example.moviebrowser.data.api.OmdbApiService
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
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OmdbApiService::class.java)
    }
    single<MovieRepository> { MovieRepositoryImpl(get(), "5b355391") }
    viewModel { MovieListViewModel(get()) }
}