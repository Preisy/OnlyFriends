package ru.onlyfriends.api.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import ru.onlyfriends.api.model.dto.exception.UnauthorizedException
import java.util.*

@Component
class JwtTokenUtil {
    private val secret = "YOUR_SECRET"
    private val expiration = 24 * 60 * 60 * 1000

    fun generateToken(username: String): String =
        Jwts.builder().setSubject(username).setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray()).compact()

    private fun getClaims(token: String) =
        Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body

    fun getEmail(token: String): String = getClaims(token).subject

    fun isTokenValid(token: String): Boolean {
        val claims = try {
            getClaims(token)
        } catch (e: Exception) {
            throw UnauthorizedException()
        }
        val expirationDate = claims.expiration
        val now = Date(System.currentTimeMillis())
        return now.before(expirationDate)
    }
}