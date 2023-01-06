package com.example.onlyfriends.utils

import com.example.onlyfriends.model.dtos.responses.exceptions.NoPosts
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ExceptionResolver {
    @ExceptionHandler(value = [NoPosts::class])
    fun jwtSignatureException(cause: NoPosts)
        = cause.asResponse()
}