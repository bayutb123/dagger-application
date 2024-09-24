package com.bayutb.core.app

import android.content.Context
import android.content.Intent

object AppRouter {
    fun go(context: Context, feature: Feature) {
        val intent = Intent()
        when (feature) {
            Feature.LOGIN -> {
                intent.setClassName(context, "com.bayutb.login.presentation.activity.LoginActivity")
            }
            Feature.CHAT -> {
                intent.setClassName(context, "com.bayutb.chat.presentation.ChatListActivity")
            }
            Feature.HOME -> {
                intent.setClassName(context, "com.bayutb.mydaggerapplication.MainActivity")
            }
        }
        context.startActivity(intent)
    }
}

enum class Feature {
    LOGIN,
    CHAT,
    HOME
}
