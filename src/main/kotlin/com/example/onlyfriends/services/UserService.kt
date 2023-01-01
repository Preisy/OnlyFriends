package com.example.onlyfriends.services

import com.example.onlyfriends.model.dtos.UserRegistration
import com.example.onlyfriends.model.table.User
import com.example.onlyfriends.repositories.UserDao
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
    private val userDao: UserDao,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    fun register(user: UserRegistration): User {
        user.password = passwordEncoder.encode(user.password)
        return userDao.save(user.asEntity())
    }
}