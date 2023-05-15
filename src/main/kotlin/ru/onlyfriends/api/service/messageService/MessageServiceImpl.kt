package ru.onlyfriends.api.service.messageService

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.onlyfriends.api.model.dto.request.MessageRequest
import ru.onlyfriends.api.model.entity.Message
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.repository.MessageRepository
import ru.onlyfriends.api.service.CrudServiceImpl
import ru.onlyfriends.api.service.userService.UserService


@Service("MessageService")
class MessageServiceImpl(
    override val repository: MessageRepository,
    val userService: UserService
) : MessageService, CrudServiceImpl<MessageRequest, Message, Long, MessageRepository>() {

    override fun getMessagesBetweenTwoUsers(recipient: String, page: Int, pageSize: Int): List<Message> {
        val author = getPrincipal()
        val recipientUser = userService.findByLogin(recipient)
        return getMessagesBetweenTwoUsers(
            author, recipientUser, PageRequest.of(page, pageSize)
        )
    }

    private fun getMessagesBetweenTwoUsers(
        author: User,
        recipient: User,
        pageable: Pageable
    ) : List<Message> = repository.findAllByRecipientAndAuthorOrAuthorAndRecipientOrderByCreatedAtDesc(
        author, recipient,
        author, recipient,
        pageable
    )

    override fun create(request: MessageRequest): Message {
        val author = getPrincipal()
        request.author = author
        request.recipient = userService.findByLogin(request.recipientLogin)
        return repository.save(request.asModel())
    }

    private fun getPrincipal(): User = SecurityContextHolder.getContext().authentication.principal as User

}