package com.bayutb.core.app

import android.content.Context
import android.content.Intent

object AppRouter {
    fun go(context: Context, feature: Feature) {
        val intent = Intent()
        when (feature) {
            Feature.LOGIN -> {}
            Feature.CHAT -> {
                intent.setClassName(context, "com.bayutb.chat.presentation.ChatListActivity")
            }
        }
        context.startActivity(intent)
    }
}

enum class Feature {
    LOGIN,
    CHAT
}
