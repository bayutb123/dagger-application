package com.bayutb.mydaggerapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.bayutb.core.repository.DataStoreRepository
import kotlinx.coroutines.flow.distinctUntilChanged

class MainViewModel(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {
    val user = dataStoreRepository.getUser().distinctUntilChanged().asLiveData()
}

@Suppress("UNCHECKED_CAST")
class MainViewModelProvider(
    private val dataStoreRepository: DataStoreRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dataStoreRepository) as T
        }
        throw IllegalArgumentException("Check MainViewModelProvider!!")
    }
}