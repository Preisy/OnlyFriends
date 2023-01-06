package com.example.onlyfriends.services.security

import com.example.onlyfriends.utils.JwtTokenUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class JwtAuthorizationFilter(
    private val jwtTokenUtil: JwtTokenUtil,
    private val service: UserDetailsService,
    authManager: AuthenticationManager,
    ) : BasicAuthenticationFilter(authManager) {

    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  chain: FilterChain) {
        val header = request.getHeader(AUTHORIZATION)
        if (header != null && header.startsWith("Bearer ")) {
            getAuthentication(header.substring(7))
                ?.also { SecurityContextHolder.getContext().authentication = it }
        }
        chain.doFilter(request, response)
    }

    private fun getAuthentication(token: String): UsernamePasswordAuthenticationToken? {
        if (!jwtTokenUtil.isTokenValid(token)) return null
        val login = jwtTokenUtil.getLogin(token)
        val user = service.loadUserByUsername(login)
        return UsernamePasswordAuthenticationToken(user, null, user.authorities)
    }
}