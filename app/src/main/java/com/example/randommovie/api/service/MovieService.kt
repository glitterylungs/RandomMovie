package com.example.randommovie.api.service

import com.example.randommovie.api.model.MovieListApi
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("titles/random")
    fun getRandomMovies(
        @Query("list") list: String,
        @Query("limit") limit: Int
    ): Single<MovieListApi>
}