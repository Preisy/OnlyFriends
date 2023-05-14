package ru.onlyfriends.api.service.messageService

import org.springframework.data.repository.CrudRepository
import ru.onlyfriends.api.model.dto.request.MessageRequest
import ru.onlyfriends.api.model.entity.Message
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.service.CrudService
import java.time.LocalDateTime

interface MessageService : CrudService<MessageRequest, Message, Long> {
    fun getMessagesBetweenTwoUsers(
        user1: User,
        user2: User,
        beforeDate: LocalDateTime,
        page: Int,
        pageSize: Int
    ) : List<Message>

    fun getMessagesBetweenTwoUsers(
        recipient: String,
        beforeDate: String,
        page: Int,
        pageSize: Int
    ) : List<Message>


}