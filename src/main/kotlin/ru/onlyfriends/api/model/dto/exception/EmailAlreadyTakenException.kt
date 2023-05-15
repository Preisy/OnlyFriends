package ru.onlyfriends.api.model.dto.exception

import org.springframework.http.HttpStatus

class EmailAlreadyTakenException : AbstractApiException(
    HttpStatus.CONFLICT,
    "Email already taken"
)