package ru.onlyfriends.api.model.dto.exception

import org.springframework.http.HttpStatus

class InternalServerErrorException : AbstractApiException(
    HttpStatus.INTERNAL_SERVER_ERROR,
    "Internal server error"
)