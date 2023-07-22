package com.example.randommovie.mapper

import com.example.randommovie.database.entity.FavouriteMovieDb
import com.example.randommovie.repository.model.FavouriteMovie

interface FavouriteMovieDbToFavouriteMovieMapper : Mapper<FavouriteMovieDb, FavouriteMovie>

class FavouriteMovieDbToFavouriteMovieMapperImpl : FavouriteMovieDbToFavouriteMovieMapper {

    override fun map(input: FavouriteMovieDb): FavouriteMovie =
        FavouriteMovie(
            id = input.id,
            title = input.title
        )
}