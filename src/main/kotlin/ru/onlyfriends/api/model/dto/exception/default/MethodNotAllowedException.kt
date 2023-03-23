package ru.onlyfriends.api.model.dto.exception.default

import org.springframework.http.HttpStatus
import ru.onlyfriends.api.model.dto.exception.AbstractApiException

class MethodNotAllowedException : AbstractApiException(
    HttpStatus.METHOD_NOT_ALLOWED,
    "Method not allowed"
)