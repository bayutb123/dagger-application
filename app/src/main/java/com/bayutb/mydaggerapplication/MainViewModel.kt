package com.bayutb.mydaggerapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bayutb.core.domain.model.User
import com.bayutb.core.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    val user = dataStoreRepository.getLoggedInUser().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = User(999, "", "")
    )

    fun logout() {
        viewModelScope.launch {
            dataStoreRepository.deleteSession()
        }
    }

    companion object {
        val DS_REPOSITORY_KEY = object : CreationExtras.Key<DataStoreRepository> {}
        val Factory = viewModelFactory {
            initializer {
                val dataStoreRepository = this[DS_REPOSITORY_KEY] as DataStoreRepository
                MainViewModel(dataStoreRepository)
            }
        }
    }
}
