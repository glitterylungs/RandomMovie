package com.example.randommovie

import android.app.Application
import com.example.randommovie.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                networkModule,
            )
        }
    }
}