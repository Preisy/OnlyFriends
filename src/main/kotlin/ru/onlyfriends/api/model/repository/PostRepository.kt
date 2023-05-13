package ru.onlyfriends.api.model.repository

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.onlyfriends.api.model.entity.Post
import ru.onlyfriends.api.model.entity.User
import java.time.LocalDateTime

@Repository
interface PostRepository : CrudRepository<Post, Long> {
    fun findAllByAuthorOrderByCreatedAtDesc(
        author: User,
        pageable: Pageable) : List<Post>

    fun findAllByAuthorInAndCreatedAtLessThanOrderByCreatedAt(
        author: MutableCollection<User>,
        createdAt: LocalDateTime,
        of: PageRequest
    ): List<Post>

    fun findAllByAuthorInOrderByCreatedAtDesc(
        author: MutableCollection<User>,
        of: PageRequest
    ): List<Post>
}