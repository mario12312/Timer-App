package com.example.timerapp.base

import androidx.multidex.MultiDexApplication
import com.example.timerapp.extensions.Ext
import com.example.timerapp.helpers.SessionManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TimerAppApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        SessionManager.context = this
        Ext.with(this)
    }
}