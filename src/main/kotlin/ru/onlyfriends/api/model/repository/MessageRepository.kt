package ru.onlyfriends.api.model.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.onlyfriends.api.model.entity.Message
import ru.onlyfriends.api.model.entity.User

@Repository
interface MessageRepository : JpaRepository<Message, Long> {

    fun findAllByRecipientAndAuthorOrAuthorAndRecipientOrderByCreatedAtDesc (
        recipient: User,
        author: User,
        author2: User,
        recipient2: User,
        pageable: Pageable
    ): List<Message>

}