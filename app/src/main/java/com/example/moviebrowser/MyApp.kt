package com.example.moviebrowser

import android.app.Application
import com.example.moviebrowser.di.appModule
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}