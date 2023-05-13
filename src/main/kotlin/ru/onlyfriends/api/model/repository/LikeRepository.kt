package ru.onlyfriends.api.model.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.entity.likes.Like

@Repository
interface LikeRepository : CrudRepository<Like, Long> {
    fun findByUserAndTargetTypeAndTargetId(user: User, targetType: String, targetId: Long): Like?
    fun countByTargetTypeAndTargetId(targetType: String, targetId: Long): Long

    fun findAllByTargetTypeAndTargetIdOrderByCreatedAtDesc(
        targetType: String,
        targetId: Long,
        pageable: Pageable
    ): List<Like>
}