package ru.onlyfriends.api.utils

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.onlyfriends.api.model.dto.ApiResponse
import ru.onlyfriends.api.model.dto.exception.AbstractApiException


@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    //    @ExceptionHandler(value = [InternalServerError])
    @ExceptionHandler(value = [AbstractApiException::class])
    protected fun handle(
        cause: AbstractApiException,
        request: WebRequest
    ): ResponseEntity<ApiResponse> {
//        logger.info(cause.toString())
        return cause.asResponse()
    }

//    @ExceptionHandler(value = [Throwable::class])
//    protected fun handle(
//        cause: Throwable,
//        request: WebRequest
//    ): ResponseEntity<ApiResponse> {
//        logger.error(cause.toString())
//        return InternalServerErrorException().asResponse()
//    }

//    @ExceptionHandler(value = [AuthenticationException::class, MissingCsrfTokenException::class, InvalidCsrfTokenException::class, SessionAuthenticationException::class])
//    fun handleAuthenticationException(
//        ex: RuntimeException?,
//        request: WebRequest
//    ): ResponseEntity<ApiResponse> {
//        return UnauthorizedException().asResponse()
//    }
}