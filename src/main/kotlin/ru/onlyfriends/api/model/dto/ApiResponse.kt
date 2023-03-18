package ru.onlyfriends.api.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

interface ApiResponse {
    val status: HttpStatus
    val message: String
    @get:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    val timestamp: LocalDateTime

    fun asResponse() = ResponseEntity.status(status).body(this)
}
