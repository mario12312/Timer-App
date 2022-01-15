package com.example.timerapp.helpers

import android.annotation.SuppressLint
import android.content.Context
import com.example.timerapp.extensions.*

@SuppressLint("StaticFieldLeak")
object SessionManager {

    @get:Synchronized
    var context: Context? = null

    private const val KEY_REMEMBER_SIGNED_IN = "KEY_REMEMBER_SIGNED_IN"
    private const val KEY_USER_TIME = "KEY_USER_TIME"
    private const val KEY_USER_LOGIN_DATE = "KEY_USER_LOGIN_DATE"



    var rememberSignedIn: Boolean
        get() {
            return spGetBoolean(KEY_REMEMBER_SIGNED_IN, false)
        }
        set(value) {
            spSetBoolean(KEY_REMEMBER_SIGNED_IN, value)
        }

    var userTime: Long?
        get() {
            return spGetLong(KEY_USER_TIME, 0)
        }
        set(value) {
            spSetLong(KEY_USER_TIME, value?: 0)
        }

    var userLoginDate: String?
        get() {
            return spGetString(KEY_USER_LOGIN_DATE, "")
        }
        set(value) {
            spSetString(KEY_USER_LOGIN_DATE, value?: "")
        }
}