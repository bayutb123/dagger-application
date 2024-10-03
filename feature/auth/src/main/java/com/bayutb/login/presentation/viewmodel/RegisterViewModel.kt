package com.bayutb.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bayutb.core.domain.model.ResultCode
import com.bayutb.core.domain.model.User
import com.bayutb.login.domain.repository.RegisterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel(
    private val registerRepository: RegisterRepository
) : ViewModel() {
    private val _navigateToLogin = Channel<Boolean>()
    val navigateToLogin = _navigateToLogin.receiveAsFlow().asLiveData()

    fun register(userName: String, password: String, dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        viewModelScope.launch(dispatcher) {
            val result = registerRepository.register(User(0, userName, password))
            when (result) {
                ResultCode.SUCCESS -> {
                    _navigateToLogin.send(true)
                }

                ResultCode.FAILED -> {
                    _navigateToLogin.send(false)
                }
            }
        }
    }

    companion object {
        val R_REPOSITORY_KEY = object : CreationExtras.Key<RegisterRepository> {}
        val Factory = viewModelFactory {
            initializer {
                val registerRepository = this[R_REPOSITORY_KEY]!!
                RegisterViewModel(registerRepository)
            }
        }
    }
}