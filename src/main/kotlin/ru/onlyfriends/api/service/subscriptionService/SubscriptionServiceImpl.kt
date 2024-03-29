package ru.onlyfriends.api.service.subscriptionService

import org.springframework.data.domain.PageRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.onlyfriends.api.model.dto.request.SubscriptionRequest
import ru.onlyfriends.api.model.dto.responses.MessageResponse
import ru.onlyfriends.api.model.entity.Subscription
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.repository.SubscriptionRepository
import ru.onlyfriends.api.model.repository.UserRepository
import ru.onlyfriends.api.service.CrudServiceImpl

@Service
class SubscriptionServiceImpl(
    override val repository: SubscriptionRepository,
    val userRepository: UserRepository
) : CrudServiceImpl<
        SubscriptionRequest, Subscription, Long, SubscriptionRepository
        >(), SubscriptionService {

    //TODO return message(done or not done)
    override fun create(request: SubscriptionRequest) =
        request.setData().run {
            repository.findByBloggerAndSubscriber(blogger, subscriber)
                ?: repository.save(asModel())
        }

    fun SubscriptionRequest.setData() = this.apply {
        subscriber = getPrincipal()
        blogger = userRepository.findById(bloggerId).get()
    }

    //TODO return message(done or not done)
    //TODO check for self subscripting
    override fun subscribe(request: SubscriptionRequest) = create(request)


    @Transactional
    override fun unsubscribe(request: SubscriptionRequest) =
        request.setData().run {
            object : MessageResponse("Unsubscribe") {
                val unsubscribed =
                    repository.deleteByBloggerAndSubscriber(blogger, subscriber) > 0
            }
        }


    override fun countSubscribers(request: SubscriptionRequest) =
        object : MessageResponse("count subscribers") {
            val number = request.setData().run { repository.countByBlogger(blogger) }
        }

    override fun subscribers(request: SubscriptionRequest, page: Int, pageSize: Int) =
        request.setData().run { repository.findAllByBloggerOrderByCreatedAtDesc(
            blogger,
            PageRequest.of(page, pageSize)
        ) }

    override fun subscriptions(page: Int, pageSize: Int): List<Subscription> {
        return repository.findAllBySubscriberOrderByCreatedAtDesc(
            getPrincipal(),
            PageRequest.of(page, pageSize)
        )
    }

    private fun getPrincipal(): User = SecurityContextHolder.getContext().authentication.principal as User

}