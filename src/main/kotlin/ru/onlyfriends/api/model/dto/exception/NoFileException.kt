package ru.onlyfriends.api.model.dto.exception

import org.springframework.http.HttpStatus

class NoFileException : AbstractApiException(
    HttpStatus.NO_CONTENT,
    "File not found"
)