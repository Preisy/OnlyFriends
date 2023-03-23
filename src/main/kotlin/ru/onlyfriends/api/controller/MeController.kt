package ru.onlyfriends.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.onlyfriends.api.service.meService.MeService

@RestController
@RequestMapping("/me")
class MeController(
    private val meService: MeService
) {
    @GetMapping
    fun showMe() = meService.showMe()
}