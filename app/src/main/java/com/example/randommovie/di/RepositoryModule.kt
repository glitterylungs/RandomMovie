package com.example.randommovie.di

import com.example.randommovie.repository.FavouriteMovieRepository
import com.example.randommovie.repository.FavouriteMovieRepositoryImpl
import com.example.randommovie.repository.MovieRepository
import com.example.randommovie.repository.MovieRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<MovieRepository> {
        MovieRepositoryImpl(
            movieService = get(),
            movieListApiToMovieListMapper = get()
        )
    }

    single<FavouriteMovieRepository> {
        FavouriteMovieRepositoryImpl(
            favouriteMovieDao = get(),
            favouriteMovieDbToFavouriteMovieMapper = get(),
            favouriteMovieToFavouriteMovieDbMapper = get()
        )
    }
}