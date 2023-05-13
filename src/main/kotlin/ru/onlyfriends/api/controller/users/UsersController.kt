package ru.onlyfriends.api.controller.users

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import ru.onlyfriends.api.model.dto.ApiResponse
import ru.onlyfriends.api.model.dto.message.DeletedMessage
import ru.onlyfriends.api.model.dto.request.UserRequest
import ru.onlyfriends.api.service.userService.UserService


@RestController
@RequestMapping("/users")
class UsersController(
    private val userService: UserService
) {
    @GetMapping
    fun getAll() = userService.findAll()

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    fun create(@RequestBody userRequest: UserRequest) = userService.create(userRequest)

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<ApiResponse> {
        userService.delete(id)
        return DeletedMessage().asResponse()
    }

}