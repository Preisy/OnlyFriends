package com.example.onlyfriends.model.dtos.requests

import com.example.onlyfriends.model.table.User
import java.beans.ConstructorProperties

data class LoginDto
@ConstructorProperties("login", "password")
constructor(val login: String, var password: String) {
    fun asEntity() = User(
        login = login,
        password = password
    )
}