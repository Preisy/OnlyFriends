package ru.onlyfriends.api.security

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.security.web.csrf.CsrfTokenRepository
import org.springframework.security.web.csrf.DefaultCsrfToken
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


//@Repository
//class JwtTokenRepository : CsrfTokenRepository {
//    public val secret = "springrest"
//    override fun generateToken(httpServletRequest: HttpServletRequest): CsrfToken {
//        val id: String = UUID.randomUUID().toString().replace("-", "")
//        val now = Date()
//        val exp: Date = Date.from(
//            LocalDateTime.now().plusMinutes(30)
//                .atZone(ZoneId.systemDefault()).toInstant()
//        )
//        var token: String? = ""
//        try {
//            token = Jwts.builder()
//                .setId(id)
//                .setIssuedAt(now)
//                .setNotBefore(now)
//                .setExpiration(exp)
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact()
//        } catch (e: JwtException) {
//            e.printStackTrace()
//            //ignore
//        }
//        return DefaultCsrfToken("x-csrf-token", "_csrf", token)
//    }
//
//    override fun saveToken(csrfToken: CsrfToken?, request: HttpServletRequest?, response: HttpServletResponse) {
//        if (csrfToken != null) {
//            if (!response.headerNames.contains(ACCESS_CONTROL_EXPOSE_HEADERS))
//                response.addHeader(ACCESS_CONTROL_EXPOSE_HEADERS, csrfToken.getHeaderName());
//
//            if (response.headerNames.contains(csrfToken.headerName))
//                response.setHeader(csrfToken.headerName, csrfToken.token);
//            else
//                response.addHeader(csrfToken.headerName, csrfToken.token);
//        }
//    }
//    override fun loadToken(request: HttpServletRequest): CsrfToken? {
//        return (request.getAttribute(CsrfToken::class.java.name) as CsrfToken)
//    }
//    fun clearToken(response: HttpServletResponse) {
//        if (response.headerNames.contains("x-csrf-token")) response.setHeader("x-csrf-token", "")
//    }
//
//}