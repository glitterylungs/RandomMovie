package com.example.randommovie.api.model

data class Movie(
    val titleText: TitleText,
    val releaseYear: ReleaseYear,
    val primaryImage: PrimaryImage
)

data class PrimaryImage(
    val url: String
)

data class TitleText(
    val text: String
)

data class ReleaseYear(
    val year: Int
)