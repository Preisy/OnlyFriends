package ru.onlyfriends.api.service.chatService

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import ru.onlyfriends.api.model.dto.chat.ChatMessageRequest
import ru.onlyfriends.api.model.dto.chat.ChatMessageResponse
import ru.onlyfriends.api.model.dto.exception.ChatError
import ru.onlyfriends.api.model.dto.request.MessageRequest
import ru.onlyfriends.api.model.entity.Message
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.repository.MessageRepository
import ru.onlyfriends.api.service.CrudServiceImpl
import ru.onlyfriends.api.service.messageService.MessageService
import ru.onlyfriends.api.service.userService.UserService

/**
 * MessageService should not be used
 */
@Component
class ChatHandlerImpl(
    val userService: UserService,
    val messageRepository: MessageRepository,
) : TextWebSocketHandler(), ChatHandler {
    val sessions = mutableMapOf<Long, MutableList<WebSocketSession>>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
        synchronized(sessions) {
            sessions.merge(getAuthor(session).id, mutableListOf(session)) { prev, one ->
                prev.also { it += one }
            }
        }
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        super.handleTextMessage(session, message)
        val messageRequest = getRequestMessage(message)
        val messageEntity = getMessage(session, messageRequest)
        saveMessage(messageEntity)
        sendMessage(messageEntity)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        super.afterConnectionClosed(session, status)
        synchronized(sessions) {
            sessions.merge(getAuthor(session).id, mutableListOf(session)) { prev, one ->
                prev.also { it -= one[0] }
            }
        }
    }

    private fun getAuthor(session: WebSocketSession) = userService.findByLogin(
        session.principal?.name ?: throw ChatError()
    )

    private fun getMessage(session: WebSocketSession, messageRequest: ChatMessageRequest) = Message(
        messageRequest.text,
        getAuthor(session),
        userService.findByLogin(messageRequest.recipientLogin)
    )

    private fun getRequestMessage(message: TextMessage) =
        ObjectMapper().readValue(message.payload, ChatMessageRequest::class.java) ?: throw ChatError()

    private fun sendMessage(message: Message) {
        val messageResponse = ChatMessageResponse(message)
        val mapper = ObjectMapper()
        mapper.findAndRegisterModules()
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        val json = mapper.writeValueAsString(messageResponse)
        val textMessage = TextMessage(json)
        synchronized(sessions) {
            sessions[message.recipient.id]?.forEach {
                it.sendMessage(textMessage)
            }
            sessions[message.author.id]?.forEach {
                it.sendMessage(textMessage)
            }
        }
    }

    private fun saveMessage(message: Message) {
        messageRepository.save(message)
    }

    override fun removeAllUserSessions(user: User) {
        synchronized(sessions) {
            sessions.remove(user.id)
        }
    }

}