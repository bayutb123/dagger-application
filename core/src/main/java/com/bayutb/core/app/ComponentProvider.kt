package com.bayutb.core.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ComponentProvider<T>(
    val component: T
) : ViewModel() {

    @Suppress("UNCHECKED_CAST")
    class ComponentProviderFactory<T>(
        private val component: T
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ComponentProvider(component) as T
        }
    }
}