package com.bayutb.chat.di.module

import com.bayutb.chat.data.ChatDataSource
import com.bayutb.chat.domain.repository.ChatRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object ChatModule {
    @Provides
    fun provideGreetingString() : String {
        return "Hello ges!"
    }

    @Provides
    fun provideChatRepository(retrofit: Retrofit) : ChatRepository {
        return ChatDataSource(retrofit)
    }
}