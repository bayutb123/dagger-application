package com.bayutb.core.app

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object Home
    @Serializable
    data object Login
    @Serializable
    data object Register
    @Serializable
    data object ChatList
    @Serializable
    data class ChatRoom(val chatRoomId: Int)
}
