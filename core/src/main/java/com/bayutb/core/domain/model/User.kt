package com.bayutb.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bayutb.core.app.EncryptionHelper


@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val userName: String,
    var password: String
)

fun User.encryptPassword() : User {
    this.password = EncryptionHelper.encrypt(this.password)
    return this
}

fun User.decryptPassword() : User {
    this.password = EncryptionHelper.decrypt(this.password)
    return this
}