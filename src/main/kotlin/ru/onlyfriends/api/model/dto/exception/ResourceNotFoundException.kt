package ru.onlyfriends.api.model.dto.exception

import org.springframework.http.HttpStatus

class ResourceNotFoundException(
    resource: String
) : AbstractApiException(
    HttpStatus.NOT_FOUND,
    "$resource not found"
) {
    constructor() : this("Resource not found")
}