package com.example.onlyfriends.controllers

import com.example.onlyfriends.services.SubscriptionsService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SubscriptionsController(
    private val subsService: SubscriptionsService
) {
    @GetMapping("me/subscriptions")
    fun getSubscriptions() = subsService.subscriptions()

    @PostMapping("me/subscription")
    fun subscribe(@RequestParam authorLogin: String) = subsService.subscribe(authorLogin)

    @DeleteMapping("me/subscription")
    fun unsubscribe(@RequestParam authorLogin: String) = subsService.unsubscribe(authorLogin)

}