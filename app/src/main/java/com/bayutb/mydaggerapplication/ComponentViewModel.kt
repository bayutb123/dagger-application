package com.bayutb.mydaggerapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.bayutb.core.di.getComponent
import com.bayutb.mydaggerapplication.di.DaggerMainComponent
import com.bayutb.mydaggerapplication.di.MainComponent

class ComponentViewModel(
    val mainComponent: MainComponent
) : ViewModel() {

    @Suppress("UNCHECKED_CAST")
    companion object {
        val Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = extras[APPLICATION_KEY]!!
                val appComponent = application.getComponent()

                val mainComponent = DaggerMainComponent.builder()
                    .appComponent(appComponent)
                    .build()

                return ComponentViewModel(mainComponent) as T
            }
        }
    }
}