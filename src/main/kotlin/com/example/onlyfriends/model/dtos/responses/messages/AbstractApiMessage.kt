package com.example.onlyfriends.model.dtos.responses.messages

import com.example.onlyfriends.model.dtos.responses.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

abstract class AbstractApiMessage (
    override val status: HttpStatus,
    override val message: String
) : ApiResponse