package com.bayutb.chat.data

import android.util.Log
import com.bayutb.chat.domain.model.Chat
import com.bayutb.chat.domain.repository.ChatRepository
import kotlinx.coroutines.delay
import retrofit2.Retrofit

class ChatDataSource(
    private val retrofit: Retrofit
): ChatRepository {
    override suspend fun getChatList(): List<Chat> {
        Log.d("Dagger", "retrofit : $retrofit")
        delay(1000)
        val dummyChats = mutableListOf<Chat>()
        for (i in 1..10) {
            dummyChats.add(
                Chat(
                    id = i,
                    sender = "Sender $i",
                    msg = "This is message $i"
                )
            )
        }
        return dummyChats
    }
}