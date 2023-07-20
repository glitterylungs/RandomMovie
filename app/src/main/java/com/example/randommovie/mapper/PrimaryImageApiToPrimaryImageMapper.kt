package com.example.randommovie.mapper

import com.example.randommovie.api.model.PrimaryImageApi
import com.example.randommovie.repository.model.PrimaryImage

interface PrimaryImageApiToPrimaryImageMapper : Mapper<PrimaryImageApi, PrimaryImage>

class PrimaryImageApiToPrimaryImageMapperImpl : PrimaryImageApiToPrimaryImageMapper {

    override fun map(input: PrimaryImageApi): PrimaryImage =
        PrimaryImage(
            url = input.url
        )
}