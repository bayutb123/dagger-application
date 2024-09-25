package com.bayutb.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bayutb.core.domain.model.ResultCode
import com.bayutb.core.domain.model.User
import com.bayutb.login.domain.repository.RegisterRepository
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

    fun register(userName: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
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
}

@Suppress("UNCHECKED_CAST")
class RegisterViewModelFactory @Inject constructor(
    private val registerRepository: RegisterRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(registerRepository) as T
        }
        throw IllegalArgumentException("Check RegisterViewModelProvider!!")
    }
}