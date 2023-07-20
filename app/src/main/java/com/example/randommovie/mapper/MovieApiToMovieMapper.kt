package com.example.randommovie.mapper

import com.example.randommovie.api.model.MovieApi
import com.example.randommovie.repository.model.Movie

interface MovieApiToMovieMapper : Mapper<MovieApi, Movie>

class MovieApiToMovieMapperImpl(
    private val titleTextApiToTitleTextMapper: TitleTextApiToTitleTextMapper,
    private val releaseYearApiToReleaseYearMapper: ReleaseYearApiToReleaseYearMapper,
    private val primaryImageApiToPrimaryImageMapper: PrimaryImageApiToPrimaryImageMapper
) : MovieApiToMovieMapper {

    override fun map(input: MovieApi): Movie =
        Movie(
            titleText = titleTextApiToTitleTextMapper.map(input = input.titleText),
            releaseYear = releaseYearApiToReleaseYearMapper.map(input = input.releaseYear),
            primaryImage = primaryImageApiToPrimaryImageMapper.map(input = input.primaryImage)
        )
}