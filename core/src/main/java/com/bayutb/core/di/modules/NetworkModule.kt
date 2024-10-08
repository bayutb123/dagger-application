package com.bayutb.core.di.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitClient() : Retrofit {
        val gson = GsonConverterFactory.create()
        return Retrofit.Builder()
            .baseUrl("https://music.youtube.com/")
            .addConverterFactory(gson)
            .build()
    }

}