package com.bayutb.login.di

import com.bayutb.core.di.AppComponent
import com.bayutb.login.presentation.activity.LoginActivity
import dagger.Component

@AuthScope
@Component(dependencies = [AppComponent::class], modules = [LoginModule::class])
interface AuthComponent {
    fun inject(loginActivity: LoginActivity)
}