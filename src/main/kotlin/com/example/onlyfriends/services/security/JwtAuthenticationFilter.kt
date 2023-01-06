package com.example.onlyfriends.services.security

import com.example.onlyfriends.utils.JwtTokenUtil
import com.example.onlyfriends.model.dtos.requests.LoginDto
import com.example.onlyfriends.model.security.UserSecurity
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtAuthenticationFilter (
    private val jwtTokenUtil: JwtTokenUtil,
    private val authenticationManager: AuthenticationManager
) : UsernamePasswordAuthenticationFilter() {
    override fun attemptAuthentication(request: HttpServletRequest,
                                       response: HttpServletResponse): Authentication {
        val credentials =
            ObjectMapper().readValue(request.inputStream, LoginDto::class.java)
        return authenticationManager.authenticate(credentials.run {
            UsernamePasswordAuthenticationToken(login, password)
        })
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        chain: FilterChain?,
        authResult: Authentication
    ) {
        val username = (authResult.principal as UserSecurity).username
        val token: String = jwtTokenUtil.generateToken(username)
        response.addHeader(HttpHeaders.AUTHORIZATION, token)
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException
    ) {
        response.status = 401
        response.contentType = "application/json"
        response.writer.append(ObjectMapper()
            .writeValueAsString(mapOf("message" to "Email or password incorrect")))
    }
}












