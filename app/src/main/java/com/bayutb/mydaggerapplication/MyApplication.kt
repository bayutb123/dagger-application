package com.bayutb.mydaggerapplication

import android.app.Activity
import android.app.Application
import android.util.Log
import com.bayutb.core.di.AppComponent
import com.bayutb.core.di.DaggerAppComponent
import com.bayutb.core.modules.LocalStorageModule
import com.bayutb.mydaggerapplication.di.DaggerMainComponent
import retrofit2.Retrofit
import javax.inject.Inject

class MyApplication : Application() {
    lateinit var appComponent: AppComponent
    @Inject
    lateinit var retrofit: Retrofit
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().localStorageModule(LocalStorageModule(this)).build()

    }
}

fun Application.getComponent() : AppComponent {
    return (this as MyApplication).appComponent
}