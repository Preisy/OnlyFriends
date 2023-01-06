package com.example.onlyfriends.model.dtos.responses.exceptions

import com.example.onlyfriends.model.dtos.responses.ApiResponse
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.http.HttpStatus

@JsonIgnoreProperties("cause", "stackTrace", "suppressed", "localizedMessage")
abstract class AbstractApiException (
    override val status: HttpStatus,
    override val message: String
) : ApiResponse, Throwable() {
}