package com.example.onlyfriends.model.dtos.responses.messages

import org.springframework.http.HttpStatus

class HealthMessage : AbstractApiMessage(
    status = HttpStatus.OK,
    message = "Api is working"
)