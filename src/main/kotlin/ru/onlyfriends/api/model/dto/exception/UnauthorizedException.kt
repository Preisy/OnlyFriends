package ru.onlyfriends.api.model.dto.exception

import org.springframework.http.HttpStatus

class UnauthorizedException : AbstractApiException(
    HttpStatus.UNAUTHORIZED,
    "Unauthorized"
)