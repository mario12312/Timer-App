package com.example.timerapp.extensions

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.popBackStackAndNavigate(resId: Int, args: Bundle?,
                                          navOptions: NavOptions?){
    popBackStack()
    navigate(resId, args, navOptions)
}