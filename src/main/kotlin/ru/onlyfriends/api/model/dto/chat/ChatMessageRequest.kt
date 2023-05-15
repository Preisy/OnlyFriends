package ru.onlyfriends.api.model.dto.chat

import java.beans.ConstructorProperties

data class ChatMessageRequest
@ConstructorProperties("recipient_login", "text")
constructor(
    val recipientLogin: String,
    val text: String
)