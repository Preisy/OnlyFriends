package ru.onlyfriends.api.controller.me

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.onlyfriends.api.model.dto.request.FileRequest
import ru.onlyfriends.api.service.meService.MeService

@RestController
@RequestMapping("/me")
class MeController(
    private val meService: MeService
) {
    @GetMapping
    fun showMe() = meService.showMe()

    @PostMapping("/picture")
    fun setPicture(@RequestBody file: FileRequest) = meService.setPicture(file)
}