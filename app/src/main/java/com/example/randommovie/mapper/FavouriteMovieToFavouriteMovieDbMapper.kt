package com.example.randommovie.mapper

import com.example.randommovie.database.entity.FavouriteMovieDb
import com.example.randommovie.repository.model.FavouriteMovie

interface FavouriteMovieToFavouriteMovieDbMapper : Mapper<FavouriteMovie, FavouriteMovieDb>

class FavouriteMovieToFavouriteMovieDbMapperImpl : FavouriteMovieToFavouriteMovieDbMapper {

    override fun map(input: FavouriteMovie): FavouriteMovieDb =
        FavouriteMovieDb(
            id = input.id,
            title = input.title
        )
}