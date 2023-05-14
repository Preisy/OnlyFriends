package ru.onlyfriends.api.model.dto.chat

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import ru.onlyfriends.api.model.entity.Message
import java.beans.ConstructorProperties
import java.time.LocalDateTime

@JsonSerialize
open class ChatMessageResponse(
    val author: String,
    val text: String,
    @JsonProperty("created_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime,
    val status: Status = Status.SENT
) {
    constructor(message: Message) : this(message.author.email, message.text, message.createdAt)
    enum class Status(val status: String) {
        SENDING("Sending"),
        SENT("Sent")
    }
}