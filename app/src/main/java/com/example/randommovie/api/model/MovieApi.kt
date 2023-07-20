package com.example.randommovie.api.model

data class MovieApi(
    val titleText: TitleTextApi?,
    val releaseYear: ReleaseYearApi?,
    val primaryImage: PrimaryImageApi?
)

data class PrimaryImageApi(
    val url: String?
)

data class TitleTextApi(
    val text: String?
)

data class ReleaseYearApi(
    val year: Int?
)