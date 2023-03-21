package ru.onlyfriends.api.service.authService

import ru.onlyfriends.api.model.dto.TokenResponse
import ru.onlyfriends.api.model.dto.request.SignUpRequest
import ru.onlyfriends.api.model.entity.User

interface AuthService {
    fun signUp(signUpRequest: SignUpRequest): TokenResponse
}