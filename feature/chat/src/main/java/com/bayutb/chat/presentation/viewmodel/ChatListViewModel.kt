package com.bayutb.chat.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bayutb.chat.domain.model.Chat
import com.bayutb.chat.domain.repository.ChatRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

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
}

@Suppress("UNCHECKED_CAST")
class ChatListViewModelFactory @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatListViewModel::class.java)) {
            return ChatListViewModel(chatRepository) as T
        }
        throw IllegalArgumentException("Check ViewModel Factory!!")
    }
}