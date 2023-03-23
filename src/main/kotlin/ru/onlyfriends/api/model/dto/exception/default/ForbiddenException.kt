package ru.onlyfriends.api.model.dto.exception.default

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import ru.onlyfriends.api.model.dto.exception.AbstractApiException

class ForbiddenException : AbstractApiException(
    HttpStatus.FORBIDDEN,
    "Forbidden"
), AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {
        response!!.status = status.value()

        val ow: ObjectWriter = ObjectMapper().registerModule(JavaTimeModule()).writer().withDefaultPrettyPrinter()
        val json: String = ow.writeValueAsString(asResponse().body)
        response.writer.print(json)
    }
}