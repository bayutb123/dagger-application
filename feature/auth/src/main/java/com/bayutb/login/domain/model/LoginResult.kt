package com.bayutb.login.domain.model

import com.bayutb.core.domain.model.User

data class LoginResult(
    val loginResultCode: LoginResultCode,
    val user: User
)

enum class LoginResultCode {
    SUCCESS,
    FAILED
}
