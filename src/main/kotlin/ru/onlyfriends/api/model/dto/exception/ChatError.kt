package ru.onlyfriends.api.model.dto.exception

import org.springframework.http.HttpStatus

class ChatError : AbstractApiException(
    HttpStatus.EXPECTATION_FAILED,
    "Chat error"
)