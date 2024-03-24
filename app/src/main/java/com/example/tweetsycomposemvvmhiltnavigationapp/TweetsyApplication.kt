package com.example.tweetsycomposemvvmhiltnavigationapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TweetsyApplication :Application() {
    override fun onCreate() {
        super.onCreate()
    }
}