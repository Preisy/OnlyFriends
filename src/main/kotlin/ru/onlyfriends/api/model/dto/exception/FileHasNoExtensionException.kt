package ru.onlyfriends.api.model.dto.exception

import org.springframework.http.HttpStatus

class FileHasNoExtensionException : AbstractApiException(
    HttpStatus.BAD_REQUEST,
    "File not found"
)