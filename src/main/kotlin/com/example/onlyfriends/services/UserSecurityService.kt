package com.example.onlyfriends.services

import com.example.onlyfriends.model.security.UserSecurity
import com.example.onlyfriends.repositories.UserDao
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserSecurityService (
    private val userRepository: UserDao
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByLogin(username)?:
            throw UsernameNotFoundException("$username not found")
        return user.run { UserSecurity(login, password) }
    }

}