package com.example.randommovie.di

import com.example.randommovie.mapper.FavouriteMovieDbToFavouriteMovieMapper
import com.example.randommovie.mapper.FavouriteMovieDbToFavouriteMovieMapperImpl
import com.example.randommovie.mapper.FavouriteMovieToFavouriteMovieDbMapper
import com.example.randommovie.mapper.FavouriteMovieToFavouriteMovieDbMapperImpl
import com.example.randommovie.mapper.MovieApiToMovieMapper
import com.example.randommovie.mapper.MovieApiToMovieMapperImpl
import com.example.randommovie.mapper.MovieListApiToMovieListMapper
import com.example.randommovie.mapper.MovieListApiToMovieListMapperImpl
import com.example.randommovie.mapper.PrimaryImageApiToPrimaryImageMapper
import com.example.randommovie.mapper.PrimaryImageApiToPrimaryImageMapperImpl
import com.example.randommovie.mapper.ReleaseYearApiToReleaseYearMapper
import com.example.randommovie.mapper.ReleaseYearApiToReleaseYearMapperImpl
import com.example.randommovie.mapper.TitleTextApiToTitleTextMapper
import com.example.randommovie.mapper.TitleTextApiToTitleTextMapperImpl
import org.koin.dsl.module

val mapperModule = module {

    single<MovieListApiToMovieListMapper> {
        MovieListApiToMovieListMapperImpl(
            movieApiToMovieMapper = get()
        )
    }

    single<MovieApiToMovieMapper> {
        MovieApiToMovieMapperImpl(
            titleTextApiToTitleTextMapper = get(),
            releaseYearApiToReleaseYearMapper = get(),
            primaryImageApiToPrimaryImageMapper = get()
        )
    }

    single<TitleTextApiToTitleTextMapper> {
        TitleTextApiToTitleTextMapperImpl()
    }

    single<ReleaseYearApiToReleaseYearMapper> {
        ReleaseYearApiToReleaseYearMapperImpl()
    }

    single<PrimaryImageApiToPrimaryImageMapper> {
        PrimaryImageApiToPrimaryImageMapperImpl()
    }

    single<FavouriteMovieDbToFavouriteMovieMapper> {
        FavouriteMovieDbToFavouriteMovieMapperImpl()
    }

    single<FavouriteMovieToFavouriteMovieDbMapper> {
        FavouriteMovieToFavouriteMovieDbMapperImpl()
    }
}