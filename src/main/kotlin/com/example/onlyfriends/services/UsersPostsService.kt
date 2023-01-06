package com.example.onlyfriends.services

import com.example.onlyfriends.model.dtos.requests.DateDto
import com.example.onlyfriends.model.dtos.requests.PostDto
import com.example.onlyfriends.model.dtos.responses.exceptions.NoPosts
import com.example.onlyfriends.model.table.UsersPost
import com.example.onlyfriends.repositories.UsersPostDao
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeParseException

@Service
class UsersPostsService (
    private val postDao: UsersPostDao,
    private val authedUser: AuthedUser
) {
    fun create(post: PostDto) = postDao.save(post.asEntity(authedUser.getUser()))

    fun delete(postId: Long) {
        if (authedUser.getLogin() == postDao.getUsersPostById(postId).user.login) {
            postDao.deleteById(postId)
        } else throw AccessDeniedException("It is not your post")
    }

    fun getLast(): UsersPost =
        postDao.findFirstByUserOrderByDateTimeDesc(authedUser.getUser()) ?: throw NoPosts()

    fun getNext(date: String) =
        try {
            postDao.findFirstByUserAndDateTimeBeforeOrderByDateTimeDesc(authedUser.getUser(),
                LocalDateTime.parse(date))
                ?: throw NoPosts()
        } catch (cause: DateTimeParseException) {
            getLast()
        }

    fun getPrev(date: String) =
        try {
            postDao.findFirstByUserAndDateTimeAfterOrderByDateTime(authedUser.getUser(),
                LocalDateTime.parse(date))
                ?: throw NoPosts()
        } catch (cause: DateTimeParseException) {
            getLast()
        }


}