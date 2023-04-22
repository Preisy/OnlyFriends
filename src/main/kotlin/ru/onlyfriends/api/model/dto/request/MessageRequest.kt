package ru.onlyfriends.api.model.dto.request

import ru.onlyfriends.api.model.entity.AbstractEntity
import ru.onlyfriends.api.model.entity.Message
import ru.onlyfriends.api.model.entity.User
import java.beans.ConstructorProperties

data class MessageRequest
@ConstructorProperties("recipient_login", "text")
constructor(
    val recipientLogin: String,
    val text: String
) : ApiRequest {
    var author: User? = null
    var recipient: User? = null
    override fun asModel() = Message(text, author!!, recipient!!)
}