package com.bayutb.chat.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bayutb.chat.domain.model.Chat
import com.bayutb.chat.domain.repository.ChatRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatListViewModel @Inject constructor(
    private val greetingString: String,
    private val chatRepository: ChatRepository
) : ViewModel() {
    private val _chats = MutableLiveData<List<Chat>>(emptyList())
    val chats: LiveData<List<Chat>> = _chats

    init {
        getChats()
    }

    private fun getChats() {
        viewModelScope.launch {
            _chats.value = chatRepository.getChatList()
        }
    }
}