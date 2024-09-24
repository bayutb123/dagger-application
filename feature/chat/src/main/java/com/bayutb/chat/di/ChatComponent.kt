package com.bayutb.chat.di

import com.bayutb.chat.presentation.ChatListActivity
import com.bayutb.chat.di.module.ChatModule
import com.bayutb.chat.presentation.fragment.ChatListFragment
import com.bayutb.chat.presentation.fragment.ChatRoomFragment
import com.bayutb.core.di.AppComponent
import com.bayutb.login.di.ChatScope
import dagger.Component
import javax.inject.Singleton

@ChatScope
@Component(dependencies = [AppComponent::class], modules = [ChatModule::class])
interface ChatComponent {
    fun inject(chatListActivity: ChatListActivity)
    fun inject(chatListFragment: ChatListFragment)
    fun inject(chatRoomFragment: ChatRoomFragment)
}