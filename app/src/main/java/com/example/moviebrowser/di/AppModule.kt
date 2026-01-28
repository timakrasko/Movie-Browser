package com.example.moviebrowser.di

import androidx.room.Room
import com.example.moviebrowser.data.api.TmdbApiService
import com.example.moviebrowser.data.db.AppDatabase
import com.example.moviebrowser.data.repository.FavoriteRepository
import com.example.moviebrowser.data.repository.FavoriteRepositoryImpl
import com.example.moviebrowser.data.repository.MovieRepository
import com.example.moviebrowser.data.repository.MovieRepositoryImpl
import com.example.moviebrowser.presentation.viewModel.FavoritesViewModel
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
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "movies_db"
        ).build()
    }
    single { get<AppDatabase>().favoriteMovieDao() }
    single<MovieRepository> { MovieRepositoryImpl(get(), "b493492000673e32c655001342ab405e") }
    single<FavoriteRepository> { FavoriteRepositoryImpl(get()) }
    viewModel { MovieListViewModel(get()) }
    viewModel { (movieId: Int) -> MovieDetailsViewModel(get(), movieId) }
    viewModel { FavoritesViewModel(get()) }
}