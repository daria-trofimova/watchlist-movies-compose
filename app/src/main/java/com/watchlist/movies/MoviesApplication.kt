package com.watchlist.movies

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy.Builder
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
internal class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setStrictModePolicy()
    }

    private fun setStrictModePolicy() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                Builder().detectAll().penaltyLog().build(),
            )
        }
    }
}