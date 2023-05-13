package ru.onlyfriends.api.controller.me

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
        @RequestParam("page") page: Int,
        @RequestParam("page_size") pageSize: Int): List<Any> =
        subscriptionService.subscriptions(page, pageSize)
}