package ru.onlyfriends.api.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.onlyfriends.api.model.entity.User
import java.util.*

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByLogin(login: String): Optional<User>
}