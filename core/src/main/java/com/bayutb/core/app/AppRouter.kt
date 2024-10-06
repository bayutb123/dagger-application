package com.bayutb.core.app

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions

object AppRouter {
    fun go(
        navController: NavController,
        feature: Feature,
        popBackStack: Boolean = false,
    ) {
        val currentRoute = navController.currentDestination?.id

        val uri = when (feature) {
            Feature.CHAT -> "android-app://com.bayutb.chat/chatlist"
            is Feature.CHATROOM -> "android-app://com.bayutb.chat/chatroom/${feature.chatId}"
        }.toUri()

        val navDeepLinkRequest = NavDeepLinkRequest.Builder.fromUri(uri)
            .build()

        val navOptions = NavOptions.Builder().apply {
            if (popBackStack) {
                currentRoute?.let { route ->
                    setPopUpTo(route, inclusive = true)
                }
            }
        }.build()

        navController.navigate(navDeepLinkRequest, navOptions)
    }
}

sealed class Feature {
    data object CHAT : Feature()
    data class CHATROOM(val chatId: Int) : Feature()
}
