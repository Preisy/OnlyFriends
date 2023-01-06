package com.example.onlyfriends.services

import com.example.onlyfriends.model.security.UserSecurity
import com.example.onlyfriends.repositories.UserDao
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthedUser (
    private val userDao: UserDao
){
    fun getLogin() = (SecurityContextHolder.getContext().authentication.principal as UserSecurity).login
    fun getUser() = userDao.findByLogin(getLogin())?:throw AccessDeniedException("login is wrong")
}