package com.bayutb.core.app

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.bayutb.core.R

object AppRouter {
    fun go(
        navController: NavController,
        feature: Feature,
        bundle: Bundle = Bundle(),
        popBackStack: Boolean = false,
    ) {
        val currentRoute = navController.currentDestination?.id
        val id = when (feature) {
            Feature.LOGIN -> R.id.loginFragment
            Feature.CHAT -> R.id.chatListFragment
            Feature.HOME -> R.id.homeFragment
            Feature.REGISTER -> R.id.registerFragment
            Feature.CHATROOM -> R.id.chatRoomFragment
        }

        val navOptions = NavOptions.Builder().apply {
            if (popBackStack) {
                currentRoute?.let { route ->
                    setPopUpTo(route, inclusive = true)
                }
            }
        }.build()

        navController.navigate(id, bundle, navOptions)
    }
}

enum class Feature {
    LOGIN,
    CHAT,
    HOME,
    REGISTER,
    CHATROOM
}
