package ru.onlyfriends.api.controller

import org.springframework.web.bind.annotation.*
import ru.onlyfriends.api.model.dto.request.UserRequest
import ru.onlyfriends.api.service.userService.UserService


@RestController
@RequestMapping("/users")
class UsersController(
    private val userService: UserService
) {
    @GetMapping
    fun getAll() = userService.findAll()

    @PostMapping
    fun create(@RequestBody userRequest: UserRequest) = userService.create(userRequest)

}