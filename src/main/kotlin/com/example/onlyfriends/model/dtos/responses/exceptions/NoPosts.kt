package com.example.onlyfriends.model.dtos.responses.exceptions

import org.springframework.http.HttpStatus

class NoPosts : AbstractApiException(
    HttpStatus.NO_CONTENT,
    "No posts"
)