package com.example.onlyfriends.repositories

import com.example.onlyfriends.model.table.User
import com.example.onlyfriends.model.table.UsersPost
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime
import java.time.LocalTime

interface UsersPostDao : CrudRepository<UsersPost, Long> {
    fun getUsersPostById(postId: Long): UsersPost
//    fun getUsersPostsByUser
//    fun findUsersPostsByUserOrderByDateTime(user: User): List<UsersPost>
    fun findFirstByUserOrderByDateTimeDesc(user: User): UsersPost?
    fun findFirstByUserAndDateTimeBeforeOrderByDateTimeDesc(user: User, date: LocalDateTime): UsersPost?
    fun findFirstByUserAndDateTimeAfterOrderByDateTime(user: User, date: LocalDateTime): UsersPost?

    fun findFirstByUserInOrderByDateTimeDesc(users: List<User>): UsersPost?
    fun findFirstByUserInAndDateTimeAfterOrderByDateTime(users: List<User>, date: LocalDateTime): UsersPost?
    fun findFirstByUserInAndDateTimeBeforeOrderByDateTimeDesc(users: List<User>, date: LocalDateTime): UsersPost?


}