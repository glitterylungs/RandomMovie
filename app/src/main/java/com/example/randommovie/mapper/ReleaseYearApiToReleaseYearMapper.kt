package com.example.randommovie.mapper

import com.example.randommovie.api.model.ReleaseYearApi
import com.example.randommovie.repository.model.ReleaseYear

interface ReleaseYearApiToReleaseYearMapper : Mapper<ReleaseYearApi?, ReleaseYear?>

class ReleaseYearApiToReleaseYearMapperImpl : ReleaseYearApiToReleaseYearMapper {

    override fun map(input: ReleaseYearApi?): ReleaseYear? =
        input?.let {
            ReleaseYear(
                year = it.year
            )
        }
}