package com.bayutb.chat.di

import com.bayutb.chat.presentation.ChatListActivity
import com.bayutb.chat.di.module.ChatModule
import com.bayutb.core.di.AppComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [AppComponent::class], modules = [ChatModule::class])
interface ChatComponent {
    fun inject(chatListActivity: ChatListActivity)
}