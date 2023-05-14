package ru.onlyfriends.api.controller

import org.springframework.data.jpa.repository.Query
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.onlyfriends.api.service.meService.MeService
import ru.onlyfriends.api.service.messageService.MessageService

@RestController
class MessagesController(
    private val messageService: MessageService,
) {
    @GetMapping("/messages/{recipient}")
    fun getMessages(
        @PathVariable recipient: String,
        @RequestParam("page") page: Int,
        @RequestParam("time_of_last_message") time: String,
        @RequestParam("page_size") pageSize: Int
    ) = messageService.getMessagesBetweenTwoUsers(recipient, time, page, pageSize)
}