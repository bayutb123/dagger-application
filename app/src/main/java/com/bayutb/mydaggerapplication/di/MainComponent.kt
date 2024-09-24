package com.bayutb.mydaggerapplication.di

import com.bayutb.core.di.AppComponent
import com.bayutb.mydaggerapplication.di.MainScope
import com.bayutb.mydaggerapplication.MainActivity
import dagger.Component
import javax.inject.Singleton

@MainScope
@Component(dependencies = [AppComponent::class], modules = [MainModule::class])
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}