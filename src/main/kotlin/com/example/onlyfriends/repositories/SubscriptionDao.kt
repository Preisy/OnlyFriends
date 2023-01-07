package com.example.onlyfriends.repositories

import com.example.onlyfriends.model.table.Subscription
import com.example.onlyfriends.model.table.User
import org.springframework.data.repository.CrudRepository

interface SubscriptionDao : CrudRepository<Subscription, Long> {
    fun findAllBySubscriber(user: User): List<Subscription>
    fun existsByAuthorAndSubscriber(author: User, subscriber: User): Boolean
    fun findByAuthorAndSubscriber(author: User, subscriber: User): Subscription?
}