package com.example.randommovie.di

import androidx.room.Room
import com.example.randommovie.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "app_database"

val databaseModule = module {

    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AppDatabase::class.java,
            name = DATABASE_NAME
        ).build()
    }

    single {
        get<AppDatabase>().favouriteMovieDao()
    }
}