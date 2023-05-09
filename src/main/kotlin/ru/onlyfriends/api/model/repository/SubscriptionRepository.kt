package ru.onlyfriends.api.model.repository

import org.springframework.data.repository.CrudRepository
import ru.onlyfriends.api.model.entity.Subscription
import ru.onlyfriends.api.model.entity.User
import java.time.LocalDateTime

interface SubscriptionRepository : CrudRepository<Subscription, Long> {

    fun findByBloggerAndSubscriber(blogger: User, subscriber: User): Subscription?
    fun deleteByBloggerAndSubscriber(blogger: User, subscriber: User): Int
    fun countByBlogger(blogger: User): Int
    fun findAllByCreatedAtLessThanAndBloggerOrderByCreatedAt(
        createdAt: LocalDateTime,
        blogger: User) : List<Subscription>

    fun findAllBySubscriber(subscriber: User): List<Subscription>
}