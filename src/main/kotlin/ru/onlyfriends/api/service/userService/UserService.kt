package ru.onlyfriends.api.service.userService

import org.springframework.security.core.userdetails.UserDetailsService
import ru.onlyfriends.api.model.dto.request.UserRequest
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.service.CrudService

interface UserService : CrudService<UserRequest, User, Long>, UserDetailsService {
    fun findByLogin(login: String): User

    fun findByPartialNickname(partialNickname: String, page: Int, pageSize: Int): List<User>

//    fun login(login: String)
}