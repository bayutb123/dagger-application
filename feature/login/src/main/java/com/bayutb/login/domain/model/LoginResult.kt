package com.bayutb.login.domain.model

data class LoginResult(
    val resultCode: ResultCode,
    val user: User
)

enum class ResultCode {
    SUCCESS,
    FAILED
}
