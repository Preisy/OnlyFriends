package com.example.onlyfriends.model.dtos.responses.exceptions

import org.springframework.http.HttpStatus

class NoSuchUser : AbstractApiException(HttpStatus.NO_CONTENT, " such user")