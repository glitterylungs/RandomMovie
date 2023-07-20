package com.example.randommovie.repository

import com.example.randommovie.api.service.MovieService
import com.example.randommovie.mapper.MovieListApiToMovieListMapper
import com.example.randommovie.repository.model.MovieList
import io.reactivex.rxjava3.core.Single

interface MovieRepository {

    fun getRandomMovies(list: String, limit: Int): Single<MovieList>
}

class MovieRepositoryImpl(
    private val movieService: MovieService,
    private val movieListApiToMovieListMapper: MovieListApiToMovieListMapper
) : MovieRepository {

    override fun getRandomMovies(list: String, limit: Int): Single<MovieList> =
        movieService.getRandomMovies(list, limit).map { movieListApi ->
            movieListApiToMovieListMapper.map(movieListApi)
        }
}