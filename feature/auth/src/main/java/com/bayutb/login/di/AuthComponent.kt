package com.bayutb.login.di

import android.app.Application
import com.bayutb.core.di.AppComponent
import com.bayutb.core.di.getComponent
import com.bayutb.core.domain.repository.DataStoreRepository
import com.bayutb.login.domain.repository.LoginRepository
import com.bayutb.login.domain.repository.RegisterRepository
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

    fun provideLoginRepository(): LoginRepository
    fun provideDataStoreRepository(): DataStoreRepository
    fun provideRegisterRepository(): RegisterRepository
}


fun Application.getAuthComponent(): AuthComponent {
    return DaggerAuthComponent.builder()
        .appComponent(this.getComponent())
        .build()
}