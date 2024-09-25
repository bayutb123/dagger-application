package com.bayutb.login.di

import com.bayutb.core.di.AppComponent
import com.bayutb.login.presentation.activity.LoginActivity
import com.bayutb.login.presentation.activity.LoginFragment
import com.bayutb.login.presentation.activity.RegisterFragment
import dagger.Component

@AuthScope
@Component(
    dependencies = [AppComponent::class],
    modules = [LoginModule::class, RegisterModule::class]
)
interface AuthComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(registerFragment: RegisterFragment)
    fun inject(loginFragment: LoginFragment)
}