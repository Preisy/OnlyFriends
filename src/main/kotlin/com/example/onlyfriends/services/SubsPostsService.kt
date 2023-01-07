package com.example.onlyfriends.services

import com.example.onlyfriends.model.dtos.responses.exceptions.NoPosts
import com.example.onlyfriends.model.table.User
import com.example.onlyfriends.model.table.UsersPost
import com.example.onlyfriends.repositories.SubscriptionDao
import com.example.onlyfriends.repositories.UsersPostDao
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeParseException

@Service
class SubsPostsService (
    private val subsDao: SubscriptionDao,
    private val postsDao: UsersPostDao,
    private val authedUser: AuthedUser
) {
    fun getLast(): UsersPost =
        postsDao.findFirstByUserInOrderByDateTimeDesc(
            subsDao.findAllBySubscriber(authedUser.getUser()).run {
                val users = mutableListOf<User>()
                forEach { users.add(it.author) }
                users
            }
        ) ?: throw NoPosts()

    fun getNext(date: String) =
        try {
            postsDao.findFirstByUserInAndDateTimeBeforeOrderByDateTimeDesc(
                subsDao.findAllBySubscriber(authedUser.getUser()).run {
                    val users = mutableListOf<User>()
                    forEach { users.add(it.author) }
                    users
                }, LocalDateTime.parse(date)
            ) ?: throw NoPosts()
        } catch (cause: DateTimeParseException) {
            getLast()
        }

    fun getPrev(date: String) =
        try {
            postsDao.findFirstByUserInAndDateTimeAfterOrderByDateTime(
                subsDao.findAllBySubscriber(authedUser.getUser()).run {
                    val users = mutableListOf<User>()
                    forEach { users.add(it.author) }
                    users
                }, LocalDateTime.parse(date)
            ) ?: throw NoPosts()
        } catch (cause: DateTimeParseException) {
            getLast()
        }

}