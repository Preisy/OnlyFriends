package ru.onlyfriends.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.onlyfriends.api.model.dto.ApiResponse


@RestController
@RequestMapping("/health")
class HealthController {
    @GetMapping
    fun healthCheck() = "Ok"
}