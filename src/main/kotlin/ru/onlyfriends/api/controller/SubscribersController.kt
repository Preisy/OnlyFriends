package ru.onlyfriends.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.onlyfriends.api.service.subscriptionService.SubscriptionService

@RestController
@RequestMapping("/me")
class SubscribersController(
    val subscriptionService: SubscriptionService
) {
    @GetMapping("/subscriptions")
    fun getSubscribers(
        @RequestParam since: String,
        @RequestParam("page_size") pageSize: Int) =
        subscriptionService.subscriptions(since, pageSize)
}