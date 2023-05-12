package ru.onlyfriends.api.service.subscriptionService

import ru.onlyfriends.api.model.dto.ApiResponse
import ru.onlyfriends.api.model.dto.request.SubscriptionRequest
import ru.onlyfriends.api.model.dto.responses.MessageResponse
import ru.onlyfriends.api.model.entity.Subscription
import ru.onlyfriends.api.service.CrudService

interface SubscriptionService : CrudService<SubscriptionRequest, Subscription, Long> {
    fun subscribe(request: SubscriptionRequest): Subscription
    fun unsubscribe(request: SubscriptionRequest): MessageResponse
    fun countSubscribers(request: SubscriptionRequest): ApiResponse
    fun subscribers(request: SubscriptionRequest, since: String, pageSize: Int): List<Subscription>
    fun subscriptions(since: String, pageSize: Int): List<Subscription>
}