package com.example.randommovie.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.randommovie.database.dao.FavouriteMovieDao
import com.example.randommovie.database.entity.FavouriteMovieDb

@Database(entities = [FavouriteMovieDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favouriteMovieDao(): FavouriteMovieDao
}