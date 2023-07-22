package com.example.randommovie.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.randommovie.database.entity.FavouriteMovieDb
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteMovieDao {

    @Insert
    suspend fun insertFavouriteMovie(movie: FavouriteMovieDb)

    @Query("SELECT * FROM favourite_movies")
    suspend fun getFavouriteMovies(): Flow<List<FavouriteMovieDb>>

    @Query("DELETE FROM favourite_movies WHERE id = :id")
    suspend fun deleteFavouriteMovie(id: Int)
}