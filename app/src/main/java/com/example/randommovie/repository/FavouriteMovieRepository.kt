package com.example.randommovie.repository

import com.example.randommovie.database.dao.FavouriteMovieDao
import com.example.randommovie.mapper.FavouriteMovieDbToFavouriteMovieMapper
import com.example.randommovie.mapper.FavouriteMovieToFavouriteMovieDbMapper
import com.example.randommovie.repository.model.FavouriteMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface FavouriteMovieRepository {

    suspend fun addFavouriteMovie(movie: FavouriteMovie)

    suspend fun getFavouriteMovies(): Flow<List<FavouriteMovie>>

    suspend fun deleteFavouriteMovie(id: Int)
}

class FavouriteMovieRepositoryImpl(
    private val favouriteMovieDao: FavouriteMovieDao,
    private val favouriteMovieDbToFavouriteMovieMapper: FavouriteMovieDbToFavouriteMovieMapper,
    private val favouriteMovieToFavouriteMovieDbMapper: FavouriteMovieToFavouriteMovieDbMapper
) : FavouriteMovieRepository {

    override suspend fun addFavouriteMovie(movie: FavouriteMovie) {
        favouriteMovieDao.insertFavouriteMovie(favouriteMovieToFavouriteMovieDbMapper.map(movie))
    }

    override suspend fun getFavouriteMovies(): Flow<List<FavouriteMovie>> =
        favouriteMovieDao.getFavouriteMovies().map {
            it.map { movie ->
                favouriteMovieDbToFavouriteMovieMapper.map(movie)
            }
        }

    override suspend fun deleteFavouriteMovie(id: Int) {
        favouriteMovieDao.deleteFavouriteMovie(id)
    }
}