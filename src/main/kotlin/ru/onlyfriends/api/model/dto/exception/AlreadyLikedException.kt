package ru.onlyfriends.api.model.dto.exception

import org.springframework.http.HttpStatus

class AlreadyLikedException : AbstractApiException(
    HttpStatus.CONFLICT,
    "You have already liked"
)