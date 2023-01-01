package com.example.onlyfriends.model.dtos

import com.example.onlyfriends.model.table.User

class UserRegistration (
    val login: String,
    var password: String
) {
    fun asEntity() = User(
        login = login,
        password = password
    )
}