package ru.onlyfriends.api.service.userService

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import ru.onlyfriends.api.model.dto.exception.ResourceNotFoundException
import ru.onlyfriends.api.model.dto.request.UserRequest
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.repository.UserRepository
import ru.onlyfriends.api.service.CrudServiceImpl

@Service
class UserServiceImpl(
    override val repository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) : UserService, CrudServiceImpl<UserRequest, User, Long, UserRepository>(
    User::class.simpleName
) {
    override fun create(request: UserRequest): User {
        val model = request.asModel()
        model.uPassword = passwordEncoder.encode(request.password)
        return repository.save(model)
    }


    override fun findByLogin(login: String): User = repository.findByEmail(login).orElseThrow {
        ResourceNotFoundException(modelSimpleName!!)
    }

    override fun loadUserByUsername(login: String): UserDetails {
        return findByLogin(login)
    }
}