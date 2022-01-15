package com.example.timerapp.extensions

import android.app.Application

object Ext {
    lateinit var ctx: Application

    fun with(app: Application) {
        this.ctx = app
    }
}