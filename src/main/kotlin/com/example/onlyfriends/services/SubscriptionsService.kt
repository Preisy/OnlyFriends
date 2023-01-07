package com.example.onlyfriends.services

import com.example.onlyfriends.model.dtos.responses.exceptions.NoSuchUser
import com.example.onlyfriends.model.table.Subscription
import com.example.onlyfriends.model.table.User
import com.example.onlyfriends.repositories.SubscriptionDao
import com.example.onlyfriends.repositories.UserDao
import org.springframework.stereotype.Service

@Service
class SubscriptionsService (
    private val authedUser: AuthedUser,
    private val subsDao: SubscriptionDao,
    private val userDao: UserDao
) {
    fun subscriptions(): MutableList<String> {
        val subs = subsDao.findAllBySubscriber(authedUser.getUser())
        val strs = mutableListOf<String>()
        subs.forEach { strs.add(it.author.login) }
        return strs
    }

    fun checkSubscription(author: User, subscriber: User): Boolean {
        return subsDao.existsByAuthorAndSubscriber(author, subscriber)
    }

    fun subscribe(authorLogin: String) {
        val author = userDao.findByLogin(authorLogin)?:throw NoSuchUser()
        val me = authedUser.getUser()
        if (!checkSubscription(author, me) && (author.login != me.login))
            subsDao.save(Subscription(author, me))
    }

    fun unsubscribe(authorLogin: String) {
        val author = userDao.findByLogin(authorLogin)?:throw NoSuchUser()
        val me = authedUser.getUser()
        val sub = subsDao.findByAuthorAndSubscriber(author, me)?:throw NoSuchUser()
        subsDao.deleteById(sub.id)
    }
}