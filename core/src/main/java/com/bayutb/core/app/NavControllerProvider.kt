package com.bayutb.core.app

import android.app.Activity
import androidx.navigation.NavController

interface NavControllerProvider {
    fun getNavController() : NavController
}

fun Activity.navController() : NavController {
    return (this as NavControllerProvider).getNavController()
}