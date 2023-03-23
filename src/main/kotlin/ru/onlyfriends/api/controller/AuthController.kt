package ru.onlyfriends.api.controller

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.onlyfriends.api.model.dto.TokenResponse
import ru.onlyfriends.api.model.dto.request.SignUpRequest
import ru.onlyfriends.api.service.authService.AuthService

@RestController
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest, res: HttpServletResponse): TokenResponse {
        val token = authService.signUp(signUpRequest)
        res.addHeader("Authorization", token.token)
        res.addHeader("Access-Control-Expose-Headers", "Authorization")
        return token
    }
}