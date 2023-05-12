package ru.onlyfriends.api.model.dto.responses

import org.springframework.http.HttpStatus
import ru.onlyfriends.api.model.dto.ApiResponse
import java.time.LocalDateTime

open class MessageResponse(
    override val message: String,
    override val status: HttpStatus = HttpStatus.OK,
) : ApiResponse {
    override val timestamp: LocalDateTime = LocalDateTime.now()
}