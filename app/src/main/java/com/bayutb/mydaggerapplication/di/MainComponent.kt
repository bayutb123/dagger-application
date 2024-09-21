package com.bayutb.mydaggerapplication.di

import com.bayutb.core.di.AppComponent
import com.bayutb.mydaggerapplication.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [AppComponent::class])
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}