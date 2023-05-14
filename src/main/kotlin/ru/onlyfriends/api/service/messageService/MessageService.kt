package ru.onlyfriends.api.service.messageService

import ru.onlyfriends.api.model.dto.request.MessageRequest
import ru.onlyfriends.api.model.entity.Message
import ru.onlyfriends.api.service.CrudService

interface MessageService : CrudService<MessageRequest, Message, Long> {

    fun getMessagesBetweenTwoUsers(
        recipient: String,
        page: Int,
        pageSize: Int
    ) : List<Message>


}