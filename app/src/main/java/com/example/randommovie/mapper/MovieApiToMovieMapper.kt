package com.example.randommovie.mapper

import com.example.randommovie.api.model.MovieApi
import com.example.randommovie.repository.model.Movie

interface MovieApiToMovieMapper : Mapper<MovieApi?, Movie?>

class MovieApiToMovieMapperImpl(
    private val titleTextApiToTitleTextMapper: TitleTextApiToTitleTextMapper,
    private val releaseYearApiToReleaseYearMapper: ReleaseYearApiToReleaseYearMapper,
    private val primaryImageApiToPrimaryImageMapper: PrimaryImageApiToPrimaryImageMapper
) : MovieApiToMovieMapper {

    override fun map(input: MovieApi?): Movie? =
        input?.let {
            Movie(
                titleText = titleTextApiToTitleTextMapper.map(input = it.titleText),
                releaseYear = releaseYearApiToReleaseYearMapper.map(input = it.releaseYear),
                primaryImage = primaryImageApiToPrimaryImageMapper.map(input = it.primaryImage)
            )
        }
}