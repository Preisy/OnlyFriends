package com.example.onlyfriends.repositories

import com.example.onlyfriends.model.table.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface UserDao : JpaRepository<User, Long> {
    fun findByLogin(login: String): User?
}