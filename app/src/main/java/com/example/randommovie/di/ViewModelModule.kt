package com.example.randommovie.di

import com.example.randommovie.ui.details.MovieDetailsViewModel
import com.example.randommovie.ui.favourite.FavouriteMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MovieDetailsViewModel(
            movieRepository = get(),
            favouriteMovieRepository = get()
        )
    }

    viewModel {
        FavouriteMoviesViewModel(
            favouriteMovieRepository = get()
        )
    }
}