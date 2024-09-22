package com.bayutb.chat.data

import com.bayutb.chat.domain.model.Chat
import com.bayutb.chat.domain.repository.ChatRepository
import retrofit2.Retrofit

class ChatDataSource(
    private val retrofit: Retrofit
): ChatRepository {
    private val dummyChats = (1..30).map { i ->
        Chat(
            id = i,
            sender = "Sender $i",
            msg = "This is message $i"
        )
    }

    override suspend fun getChatList(): List<Chat> {
        return dummyChats
    }

    override suspend fun getChatDetail(chatId: Int): Chat? {
        return dummyChats.firstOrNull { it.id == chatId }
    }
}