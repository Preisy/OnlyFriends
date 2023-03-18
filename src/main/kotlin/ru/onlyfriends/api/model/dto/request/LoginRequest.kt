package ru.onlyfriends.api.model.dto.request

data class LoginRequest
constructor(val email: String, val password: String)