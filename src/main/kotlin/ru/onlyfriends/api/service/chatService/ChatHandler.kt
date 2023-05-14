package ru.onlyfriends.api.service.chatService

import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.handler.TextWebSocketHandler
import ru.onlyfriends.api.model.entity.User

interface ChatHandler : WebSocketHandler {
    fun removeAllUserSessions(user: User)
}