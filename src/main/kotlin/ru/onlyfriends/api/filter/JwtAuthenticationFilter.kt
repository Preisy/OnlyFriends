package ru.onlyfriends.api.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import ru.onlyfriends.api.model.dto.TokenResponse
import ru.onlyfriends.api.model.dto.exception.BadCredentialsException
import ru.onlyfriends.api.model.dto.request.LoginRequest
import ru.onlyfriends.api.model.entity.UserRoleType
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.utils.JwtTokenUtil
import java.util.*


class JwtAuthenticationFilter(
    private val jwtTokenUtil: JwtTokenUtil,
    private val authenticationManager: AuthenticationManager
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(req: HttpServletRequest, response: HttpServletResponse): Authentication {
        val credentials = ObjectMapper().readValue(req.inputStream, LoginRequest::class.java)
        val auth = UsernamePasswordAuthenticationToken(
            credentials.email,
            credentials.password,
            Collections.singleton(SimpleGrantedAuthority("ROLE_${UserRoleType.USER}"))
        )
        return authenticationManager.authenticate(auth)
    }

    override fun successfulAuthentication(
        req: HttpServletRequest?, res: HttpServletResponse, chain: FilterChain?,
        auth: Authentication
    ) {
        val username = (auth.principal as User).username
        val token: String = jwtTokenUtil.generateToken(username)
        res.writer.append(ObjectMapper().writer().writeValueAsString(TokenResponse(token)))
        res.addHeader("Authorization", token)
        res.addHeader("Access-Control-Expose-Headers", "Authorization")
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException
    ) {
        val responseEntity = BadCredentialsException().asResponse()
        responseEntity.headers.forEach { header ->
            val chave: String = header.key
            for (valor in header.value) {
                response.addHeader(chave, valor)
            }
        }
        response.status = responseEntity.statusCode.value()
        val ow: ObjectWriter = ObjectMapper().registerModule(JavaTimeModule()).writer().withDefaultPrettyPrinter()
        val json: String = ow.writeValueAsString(responseEntity.body)
        response.writer.append(json)
    }
}