package com.bayutb.login.domain.model

import com.bayutb.core.domain.model.User

data class LoginResult(
    val resultCode: ResultCode,
    val user: User
)

enum class ResultCode {
    SUCCESS,
    FAILED
}
