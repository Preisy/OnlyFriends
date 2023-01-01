package com.example.onlyfriends.controllers

import com.example.onlyfriends.model.dtos.UserRegistration
import com.example.onlyfriends.model.security.UserSecurity
import com.example.onlyfriends.repositories.UserDao
import com.example.onlyfriends.services.UserService
import com.example.onlyfriends.utils.JwtTokenUtil
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController (
    private val userService: UserService,
    private val jwtTokenUtil: JwtTokenUtil,
    private val userDao: UserDao
) {

    @PostMapping("register")
    fun register(@RequestBody user: UserRegistration) = userService.register(user)

    @GetMapping("user")
    fun userInfo(): Map<String, String> {
        val userLogin = (SecurityContextHolder.getContext().authentication.principal as UserSecurity).login
        return mapOf("login" to userLogin)
    }
}