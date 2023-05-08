package ru.onlyfriends.api.model.dto.exception

import org.springframework.http.HttpStatus

class NotLikableException : AbstractApiException(
    HttpStatus.BAD_REQUEST,
    "This class is not likable"
) {
}