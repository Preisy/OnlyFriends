package ru.onlyfriends.api.controller

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
class SubscriptionsController(
    val subscriptionService: SubscriptionService
) {

    @GetMapping("/{email}/followers")
    fun getFollowers(
        @PathVariable email: String,
        @RequestParam since: String,
        @RequestParam("page_size") pageSize: Int) =
        subscriptionService.subscribers(
            SubscriptionRequest(email),
            since,
            pageSize)

    @PostMapping("/{email}/followers")
    fun subscribe(@PathVariable email: String) =
        subscriptionService.subscribe(SubscriptionRequest(email))

    @DeleteMapping("/{email}/followers")
    fun unsubscribe(@PathVariable email: String) =
        subscriptionService.unsubscribe(SubscriptionRequest(email))

    @GetMapping("/{email}/followers/count")
    fun countSubscribers(@PathVariable email: String) =
        subscriptionService.countSubscribers(SubscriptionRequest(email))
}