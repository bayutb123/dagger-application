package com.bayutb.mydaggerapplication.di

import com.bayutb.core.di.AppComponent
import com.bayutb.mydaggerapplication.HomeFragment
import com.bayutb.mydaggerapplication.MainActivity
import dagger.Component

@MainScope
@Component(dependencies = [AppComponent::class], modules = [MainModule::class])
interface MainComponent {
    fun inject(homeFragment: HomeFragment)
}