package ru.onlyfriends.api.model.dto.message

import org.springframework.http.HttpStatus

class DeletedMessage : AbstractApiMessage(
    HttpStatus.OK,
    "Successfully deleted"
)