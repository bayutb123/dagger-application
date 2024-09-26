package com.bayutb.mydaggerapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bayutb.core.domain.repository.DataStoreRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {
    val user = dataStoreRepository.getLoggedInUser().asLiveData()

    fun logout() {
        viewModelScope.launch {
            dataStoreRepository.deleteSession()
        }
    }
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