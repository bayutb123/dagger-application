package com.bayutb.login.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bayutb.login.domain.model.LoginResultCode
import com.bayutb.core.domain.model.User
import com.bayutb.login.domain.payload.LoginPayload
import com.bayutb.login.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {
    private var _uiState = MutableLiveData<LoginUiState>()
    var uiState: LiveData<LoginUiState> = _uiState
    private val _navigateToHome = Channel<Boolean>()
    val navigateToHome = _navigateToHome.receiveAsFlow().asLiveData()

    fun login(userName: String, password: String) {
        Log.d("Dagger", userName  + password)
        _uiState.value = LoginUiState.Loading
        val payload = LoginPayload(userName, password)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.login(payload)
            result.map { login ->
                when (login.loginResultCode) {
                    LoginResultCode.SUCCESS -> {
                        _uiState.value = LoginUiState.Success(login.user)
                        _navigateToHome.send(true)
                    }
                    LoginResultCode.FAILED -> {
                        _uiState.value = LoginUiState.Failed("Failed to login wkwk")
                        _navigateToHome.send(false)
                    }
                }
            }
        }
    }

}
sealed class LoginUiState(val user: User?, val msg: String?) {
    data class Success(val result: User) : LoginUiState(result, null)
    data class Failed(val message: String): LoginUiState(null, message)
    data object Loading: LoginUiState(null, null)
    data object Idle: LoginUiState(null, null)
}

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory @Inject constructor(
    private val repository: LoginRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("Check LoginViewModelFactory!!")
    }
}