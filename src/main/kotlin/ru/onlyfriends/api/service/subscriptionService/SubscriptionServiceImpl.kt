package ru.onlyfriends.api.service.subscriptionService

import org.springframework.data.domain.PageRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.onlyfriends.api.model.dto.request.SubscriptionRequest
import ru.onlyfriends.api.model.entity.Subscription
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.repository.SubscriptionRepository
import ru.onlyfriends.api.model.repository.UserRepository
import ru.onlyfriends.api.service.CrudServiceImpl
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class SubscriptionServiceImpl(
    override val repository: SubscriptionRepository,
    val userRepository: UserRepository
) : CrudServiceImpl<
        SubscriptionRequest, Subscription, Long, SubscriptionRepository
        >(), SubscriptionService {

    override fun create(request: SubscriptionRequest) =
        request.setData().run {
            repository.findByBloggerAndSubscriber(blogger, subscriber)
                ?: repository.save(asModel())
        }

    fun SubscriptionRequest.setData() = this.apply {
        subscriber = getPrincipal()
        blogger = userRepository.findByEmail(bloggerEmail).get()
    }

    //TODO check for self subscripting
    override fun subscribe(request: SubscriptionRequest) = create(request)


    @Transactional
    override fun unsubscribe(request: SubscriptionRequest) =
        request.setData().run { repository.deleteByBloggerAndSubscriber(blogger, subscriber) > 0 }


    override fun countSubscribers(request: SubscriptionRequest) =
        request.setData().run { repository.countByBlogger(blogger) }

    override fun subscribers(request: SubscriptionRequest, since: String, pageSize: Int) =
        request.setData().run { repository.findAllByCreatedAtLessThanAndBloggerOrderByCreatedAt(
            LocalDateTime.parse(since, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            blogger) }

    override fun subscriptions(since: String, pageSize: Int): List<Subscription> {
        return repository.findAllBySubscriberAndCreatedAtLessThanOrderByCreatedAt(
            getPrincipal(),
            LocalDateTime.parse(since, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            PageRequest.of(0, pageSize)
        )
    }

    private fun getPrincipal(): User = SecurityContextHolder.getContext().authentication.principal as User

}