package com.bayutb.login.di

import com.bayutb.core.di.AppComponent
import com.bayutb.login.presentation.fragment.LoginFragment
import com.bayutb.login.presentation.fragment.RegisterFragment
import dagger.Component

@AuthScope
@Component(
    dependencies = [AppComponent::class],
    modules = [LoginModule::class, RegisterModule::class]
)
interface AuthComponent {
    fun inject(registerFragment: RegisterFragment)
    fun inject(loginFragment: LoginFragment)
}