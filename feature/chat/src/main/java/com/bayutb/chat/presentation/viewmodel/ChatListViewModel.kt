package com.bayutb.chat.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bayutb.chat.domain.model.Chat
import com.bayutb.chat.domain.repository.ChatRepository
import kotlinx.coroutines.launch

class ChatListViewModel(
    private val chatRepository: ChatRepository
) : ViewModel() {
    private val _chats = MutableLiveData<List<Chat>>(emptyList())
    val chats: LiveData<List<Chat>> = _chats
    private val _chatDetail = MutableLiveData<Chat>()
    val chatDetail: LiveData<Chat> = _chatDetail

    init {
        getChats()
    }

    private fun getChats() {
        viewModelScope.launch {
            _chats.value = chatRepository.getChatList()
        }
    }

    fun getChatDetail(chatId: Int) {
        viewModelScope.launch {
            _chatDetail.value = chatRepository.getChatDetail(chatId)
        }
    }

    companion object {
        val C_REPOSITORY_KEY = object : CreationExtras.Key<ChatRepository> {}
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = this[C_REPOSITORY_KEY] ?: throw IllegalArgumentException("ChatRepository is required")
                ChatListViewModel(repository)
            }
        }
    }
}
