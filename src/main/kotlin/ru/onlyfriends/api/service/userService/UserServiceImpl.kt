package ru.onlyfriends.api.service.userService

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import ru.onlyfriends.api.model.dto.exception.ResourceNotFoundException
import ru.onlyfriends.api.model.dto.request.UserRequest
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.repository.UserRepository
import ru.onlyfriends.api.service.CrudServiceImpl

@Service
class UserServiceImpl(
    override val repository: UserRepository
) : UserService, CrudServiceImpl<UserRequest, User, Long, UserRepository>(
    User::class.simpleName
) {
    override fun findByLogin(login: String): User = repository.findByLogin(login).orElseThrow {
        ResourceNotFoundException(modelSimpleName!!)
    }

    override fun loadUserByUsername(login: String): UserDetails {
        val u: User = findByLogin(login)
        return org.springframework.security.core.userdetails.User(
            u.login,
            u.password,
            true,
            true,
            true,
            true,
            HashSet()
        )
    }
}