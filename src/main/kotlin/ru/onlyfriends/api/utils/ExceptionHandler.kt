package ru.onlyfriends.api.utils

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.session.SessionAuthenticationException
import org.springframework.security.web.csrf.InvalidCsrfTokenException
import org.springframework.security.web.csrf.MissingCsrfTokenException
import org.springframework.security.web.util.UrlUtils
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.onlyfriends.api.model.dto.exception.UnauthorizedException
//import ru.onlyfriends.api.security.JwtTokenRepository


//@RestControllerAdvice
//class GlobalExceptionHandler(
//    private val tokenRepository: JwtTokenRepository
//) : ResponseEntityExceptionHandler() {
//    @ExceptionHandler(value = [AuthenticationException::class, MissingCsrfTokenException::class, InvalidCsrfTokenException::class, SessionAuthenticationException::class])
//    fun handleAuthenticationException(
//        ex: RuntimeException?,
//        request: HttpServletRequest?,
//        response: HttpServletResponse
//    ): UnauthorizedException {
//        tokenRepository.clearToken(response)
//        response.status = HttpServletResponse.SC_UNAUTHORIZED
////        return ErrorInfo(UrlUtils.buildFullRequestUrl(request), "error.authorization")
//        return UnauthorizedException()
//    }
//
//}