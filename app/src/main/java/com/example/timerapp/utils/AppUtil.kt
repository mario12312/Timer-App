package com.example.timerapp.utils

import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.example.timerapp.R

object AppUtil {

    fun getNavOptions(): NavOptions =
        navOptions {
            anim {
                enter = R.anim.enter
                exit = R.anim.exit
            }
        }
}