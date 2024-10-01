package com.bayutb.login.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bayutb.core.domain.model.User
import com.bayutb.core.domain.repository.DataStoreRepository
import com.bayutb.login.domain.model.LoginResultCode
import com.bayutb.login.domain.payload.LoginPayload
import com.bayutb.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel(
    private val repository: LoginRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    private var _uiState = MutableLiveData<LoginUiState>()
    var uiState: LiveData<LoginUiState> = _uiState
    val loggedIn = dataStoreRepository.getLoggedInUser().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null
    )

    fun login(userName: String, password: String) {
        _uiState.value = LoginUiState.Loading
        val payload = LoginPayload(userName, password)
        viewModelScope.launch {
            val result = repository.login(payload)
            when (result.loginResultCode) {
                LoginResultCode.SUCCESS -> {
                    dataStoreRepository.setUser(User(result.user.id, result.user.userName, result.user.password))
                    _uiState.value = LoginUiState.Success(result.user)
                }

                LoginResultCode.FAILED -> {
                    _uiState.value = LoginUiState.Failed("Failed to login wkwk")
                }
            }
        }
    }

}

sealed class LoginUiState(val user: User?) {
    data class Success(val result: User) : LoginUiState(result)
    data class Failed(val message: String) : LoginUiState(null)
    data object Loading : LoginUiState(null)
    data object Idle : LoginUiState(null)
}

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory @Inject constructor(
    private val repository: LoginRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository, dataStoreRepository) as T
        }
        throw IllegalArgumentException("Check LoginViewModelFactory!!")
    }
}