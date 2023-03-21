package ru.onlyfriends.api.model.dto.exception

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

class UnauthorizedException : AbstractApiException(
    HttpStatus.UNAUTHORIZED,
    "Unauthorized"
), AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        response!!.status = status.value()

        val ow: ObjectWriter = ObjectMapper().registerModule(JavaTimeModule()).writer().withDefaultPrettyPrinter()
        val json: String = ow.writeValueAsString(asResponse().body)
        response.writer.print(json)
    }
}