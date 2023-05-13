package ru.onlyfriends.api.controller.users.subscribers

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.onlyfriends.api.model.dto.request.SubscriptionRequest
import ru.onlyfriends.api.service.subscriptionService.SubscriptionService

@RestController
@RequestMapping("/users")
class UsersSubscriptionsController(
    val subscriptionService: SubscriptionService
) {

    @GetMapping("/{id}/subscribers")
    fun getSubscribers(
        @PathVariable id: Long,
        @RequestParam page: Int,
        @RequestParam("page_size") pageSize: Int): List<Any> =
        subscriptionService.subscribers(
            SubscriptionRequest(id),
            page,
            pageSize)

    @PostMapping("/{id}/subscribers")
    fun subscribe(@PathVariable id: Long) =
        subscriptionService.subscribe(SubscriptionRequest(id))

    @DeleteMapping("/{id}/subscribers")
    fun unsubscribe(@PathVariable id: Long) =
        subscriptionService.unsubscribe(SubscriptionRequest(id))

    @GetMapping("/{id}/subscribers/count")
    fun countSubscribers(@PathVariable id: Long) =
        subscriptionService.countSubscribers(SubscriptionRequest(id))
}