package ru.onlyfriends.api.service.messageService

import org.springframework.data.domain.PageRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.onlyfriends.api.model.dto.request.MessageRequest
import ru.onlyfriends.api.model.entity.Message
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.repository.MessageRepository
import ru.onlyfriends.api.service.CrudServiceImpl
import ru.onlyfriends.api.service.userService.UserService
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service("MessageService")
class MessageServiceImpl(
    override val repository: MessageRepository,
    val userService: UserService
) : MessageService, CrudServiceImpl<MessageRequest, Message, Long, MessageRepository>() {
    override fun getMessagesBetweenTwoUsers(
        user1: User,
        user2: User,
        beforeDate: LocalDateTime,
        page: Int,
        pageSize: Int,
    ): List<Message> {
        val pageable = PageRequest.of(page, pageSize)
        println(beforeDate)
        return repository.
            findAllByCreatedAtBeforeAndRecipientAndAuthorOrCreatedAtBeforeAndAuthorAndRecipientOrderByCreatedAtDesc(
            beforeDate,
            user1, user2,
            beforeDate,
            user1, user2,
            pageable
        )
    }

    override fun getMessagesBetweenTwoUsers(
        recipient: String,
        beforeDate: String,
        page: Int,
        pageSize: Int
    ) : List<Message> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.parse(beforeDate, formatter)
        val author = getPrincipal()
        val recipientUser = userService.findByLogin(recipient)
        return getMessagesBetweenTwoUsers(author, recipientUser, dateTime, page, pageSize).reversed()
    }

    override fun create(request: MessageRequest): Message {
        val author = getPrincipal()
        request.author = author
        request.recipient = userService.findByLogin(request.recipientLogin)
        return repository.save(request.asModel())
    }

    private fun getPrincipal(): User = SecurityContextHolder.getContext().authentication.principal as User

}