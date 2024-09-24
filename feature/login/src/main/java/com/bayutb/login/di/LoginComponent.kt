package com.bayutb.login.di

import com.bayutb.core.di.AppComponent
import com.bayutb.login.presentation.activity.LoginActivity
import dagger.Component
import javax.inject.Singleton

@LoginScope
@Component(dependencies = [AppComponent::class], modules = [LoginModule::class])
interface LoginComponent {
    fun inject(loginActivity: LoginActivity)
}