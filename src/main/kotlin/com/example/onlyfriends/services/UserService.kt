package com.example.onlyfriends.services

import com.example.onlyfriends.model.dtos.requests.LoginDto
import com.example.onlyfriends.model.security.UserSecurity
import com.example.onlyfriends.model.table.User
import com.example.onlyfriends.repositories.UserDao
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userDao: UserDao,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val authedUser: AuthedUser
) {

    fun register(user: LoginDto): User {
        user.password = passwordEncoder.encode(user.password)
        return userDao.save(user.asEntity())
    }

    fun myInfo(): User? = authedUser.getUser()
}