package com.bayutb.mydaggerapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.distinctUntilChanged
import com.bayutb.core.repository.DataStoreRepository

class MainViewModel(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {
    val user = dataStoreRepository.getUser().asLiveData().distinctUntilChanged()
}

class MainViewModelProvider(
    private val dataStoreRepository: DataStoreRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dataStoreRepository) as T
        }
        return super.create(modelClass)
    }
}