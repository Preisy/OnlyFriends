package ru.onlyfriends.api.model.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import ru.onlyfriends.api.model.entity.like.Likable
import ru.onlyfriends.api.model.entity.like.Like

interface LikeRepository : CrudRepository<Like, Long> {

    fun findByTargetOrderByCreatedAtDesc(
        target: Likable,
        pageable: Pageable
    ): List<Like>
}