package com.example.onlyfriends.model.dtos.messages

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

abstract class AbstractApiMessage (
    val status: HttpStatus,
    val message: String
) {
    fun asResponse() = ResponseEntity.status(status).body(this)
}