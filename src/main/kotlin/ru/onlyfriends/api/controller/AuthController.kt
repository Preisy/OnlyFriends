package ru.onlyfriends.api.controller

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.service.userService.UserService


@RestController
class AuthController(
    private val service: UserService
) {

    @PostMapping("/login")
    fun getAuthUser(): User? { // todo ссанина какая то
        val auth = SecurityContextHolder.getContext().authentication ?: return null
        val principal = auth.principal
        val user = if (principal is User) principal else null
        return if (user != null) service.findByLogin(user.login) else null
    }
}