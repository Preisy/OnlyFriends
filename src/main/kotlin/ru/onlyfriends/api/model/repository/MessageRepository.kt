package ru.onlyfriends.api.model.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.onlyfriends.api.model.entity.Message
import ru.onlyfriends.api.model.entity.User
import java.time.LocalDateTime

@Repository
interface MessageRepository : JpaRepository<Message, Long> {
//    fun findAllByDateTimeLessThanAndRecipientIdAndSenderOrSenderIdAndRecipientOrderByDateTimeDesc(
//        dateTime: LocalDateTime,
//        recipient_id: Long,
//        sender: User,
//        sender_id: Long,
//        recipient: User,
//        pageable: Pageable
//    ): List<WSMessage>
    fun findAllByCreatedAtLessThanAndRecipientAndAuthorOrAuthorAndRecipientOrderByCreatedAt (
        createdAt: LocalDateTime,
        recipient: User,
        author: User,
        author2: User,
        recipient2: User,
        pageable: Pageable
    ): List<Message>

}