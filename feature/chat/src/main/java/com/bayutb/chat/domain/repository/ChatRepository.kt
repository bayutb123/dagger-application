package com.bayutb.chat.domain.repository

import com.bayutb.chat.domain.model.Chat

interface ChatRepository {
    suspend fun getChatList(): List<Chat>
    suspend fun getChatDetail(chatId: Int): Chat?
}