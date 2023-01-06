package com.example.onlyfriends.controllers

import com.example.onlyfriends.model.dtos.responses.messages.HealthMessage
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {
    @GetMapping("/health")
    fun healthCheck() = HealthMessage()
}