package com.bayutb.chat.di

import android.app.Application
import com.bayutb.chat.di.module.ChatModule
import com.bayutb.chat.domain.repository.ChatRepository
import com.bayutb.chat.presentation.fragment.ChatListFragment
import com.bayutb.chat.presentation.fragment.ChatRoomFragment
import com.bayutb.core.di.AppComponent
import com.bayutb.core.di.getComponent
import dagger.Component

@ChatScope
@Component(dependencies = [AppComponent::class], modules = [ChatModule::class])
interface ChatComponent {
    fun inject(chatListFragment: ChatListFragment)
    fun inject(chatRoomFragment: ChatRoomFragment)

    fun provideChatRepository(): ChatRepository
}

fun Application.getChatComponent(): ChatComponent {
    return DaggerChatComponent.builder()
        .appComponent(this.getComponent())
        .build()
}