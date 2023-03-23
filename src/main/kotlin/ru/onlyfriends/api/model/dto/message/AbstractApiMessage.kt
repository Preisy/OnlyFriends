package ru.onlyfriends.api.model.dto.message

import org.springframework.http.HttpStatus
import ru.onlyfriends.api.model.dto.ApiResponse
import java.time.LocalDateTime

abstract class AbstractApiMessage(
    override val status: HttpStatus,
    override val message: String
) : ApiResponse {
    override val timestamp: LocalDateTime = LocalDateTime.now()
}
