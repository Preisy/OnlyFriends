package ru.onlyfriends.api.model.dto.exception

import org.springframework.http.HttpStatus

class NicknameAlreadyTakenException : AbstractApiException(
    HttpStatus.CONFLICT,
    "Nickname already taken"
)