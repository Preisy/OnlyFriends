package ru.onlyfriends.api.model.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.onlyfriends.api.model.entity.Post
import ru.onlyfriends.api.model.entity.User
import java.time.LocalDateTime

@Repository
interface PostRepository : CrudRepository<Post, Long> {
    fun findAllByAuthorAndCreatedAtLessThanOrderByCreatedAt(
        author: User,
        createdAt: LocalDateTime,
        pageable: Pageable) : List<Post>
}