package ru.onlyfriends.api.model.dto.request

import ru.onlyfriends.api.model.entity.Subscription
import ru.onlyfriends.api.model.entity.User

class SubscriptionRequest(
    val bloggerId: Long
) : ApiRequest {
    lateinit var subscriber: User
    lateinit var blogger: User
    override fun asModel() = Subscription(blogger, subscriber)
}