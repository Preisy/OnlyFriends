package com.example.onlyfriends.utils

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenUtil {
    private val secret = "secret_password"
    private val expiration = 60 * 60 * 1000

    fun generateToken(login: String): String =
        Jwts.builder()
            .setSubject(login)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS256, secret.toByteArray()).compact()

    private fun getClaims(token: String) =
        Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body

    fun getLogin(token: String): String = getClaims(token).subject

    fun isTokenValid(token: String): Boolean {
        try {
            val claims = getClaims(token)
            val expirationDate = claims.expiration
            val now = Date(System.currentTimeMillis())
            return now.before(expirationDate)
        } catch (cause: JwtException) {
            throw AccessDeniedException(cause.message)
            TODO("Custom exception handling")
        }
    }
}