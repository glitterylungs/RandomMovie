package com.example.randommovie.mapper

import com.example.randommovie.api.model.TitleTextApi
import com.example.randommovie.repository.model.TitleText

interface TitleTextApiToTitleTextMapper : Mapper<TitleTextApi?, TitleText?>

class TitleTextApiToTitleTextMapperImpl : TitleTextApiToTitleTextMapper {

    override fun map(input: TitleTextApi?): TitleText? =
        input?.let {
            TitleText(
                text = it.text
            )
        }
}