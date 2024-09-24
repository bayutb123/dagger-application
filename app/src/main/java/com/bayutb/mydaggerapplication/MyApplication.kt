package com.bayutb.mydaggerapplication

import android.app.Application
import com.bayutb.core.di.AppComponent
import com.bayutb.core.di.DaggerAppComponent
import com.bayutb.core.modules.LocalStorageModule
import retrofit2.Retrofit
import javax.inject.Inject

class MyApplication : Application(), AppComponent.AppComponentProvider {
    lateinit var appComponent: AppComponent
    @Inject
    lateinit var retrofit: Retrofit
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().localStorageModule(LocalStorageModule(this)).build()

    }

    override fun getComponent(): AppComponent {
        return appComponent
    }
}
