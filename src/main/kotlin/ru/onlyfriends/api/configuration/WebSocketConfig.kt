package ru.onlyfriends.api.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import ru.onlyfriends.api.service.chatService.ChatHandler
import ru.onlyfriends.api.service.chatService.ChatHandlerImpl

@Configuration
@EnableWebSocket
class WebSocketConfig(
    val chatHandler: ChatHandler
) : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(webSocketHandler(), "/chat")
    }

    @Bean
    fun webSocketHandler(): WebSocketHandler {
        return chatHandler
    }
}