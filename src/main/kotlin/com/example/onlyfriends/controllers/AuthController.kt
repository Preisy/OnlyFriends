package com.example.onlyfriends.controllers

import com.example.onlyfriends.model.dtos.requests.LoginDto
import com.example.onlyfriends.model.security.UserSecurity
import com.example.onlyfriends.model.table.User
import com.example.onlyfriends.services.UserService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController (
    private val userService: UserService,
) {

    @PostMapping("register")
    fun register(@RequestBody user: LoginDto) = userService.register(user)

    @GetMapping("me")
    fun userInfo(): User? = userService.myInfo()
}