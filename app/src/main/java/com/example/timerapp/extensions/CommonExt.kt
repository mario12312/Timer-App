package com.example.timerapp.extensions

import android.app.Application

inline val app: Application
    get() = Ext.ctx