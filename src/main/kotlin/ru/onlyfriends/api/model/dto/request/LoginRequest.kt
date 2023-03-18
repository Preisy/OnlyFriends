package ru.onlyfriends.api.model.dto.request

import java.beans.ConstructorProperties

data class LoginRequest
constructor(val email: String, val password: String)