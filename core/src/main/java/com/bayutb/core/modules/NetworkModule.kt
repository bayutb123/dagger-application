package com.bayutb.core.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    fun provideRetrofitClient() : Retrofit {
        val gson = GsonConverterFactory.create()
        return Retrofit.Builder()
            .baseUrl("https://music.youtube.com/")
            .addConverterFactory(gson)
            .build()
    }

}