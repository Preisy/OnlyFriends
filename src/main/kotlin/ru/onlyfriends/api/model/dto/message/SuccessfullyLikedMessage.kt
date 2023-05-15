package ru.onlyfriends.api.model.dto.message

import org.springframework.http.HttpStatus

class SuccessfullyLikedMessage : AbstractApiMessage(
    HttpStatus.OK,
    "Successfully liked"
)