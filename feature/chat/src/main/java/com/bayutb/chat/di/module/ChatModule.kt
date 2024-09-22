package com.bayutb.chat.di.module

import com.bayutb.chat.data.ChatDataSource
import com.bayutb.chat.domain.repository.ChatRepository
import com.bayutb.chat.presentation.viewmodel.ChatListViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object ChatModule {
    @Provides
    fun provideChatRepository(retrofit: Retrofit) : ChatRepository {
        return ChatDataSource(retrofit)
    }

    @Provides
    fun provideChatListViewModelFactory(repository: ChatRepository) : ChatListViewModelFactory {
        return ChatListViewModelFactory(repository)
    }
}