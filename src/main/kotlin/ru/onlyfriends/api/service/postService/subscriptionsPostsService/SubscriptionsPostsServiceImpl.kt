package ru.onlyfriends.api.service.postService.subscriptionsPostsService

import org.springframework.data.domain.PageRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.onlyfriends.api.model.entity.Post
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.repository.PostRepository
import ru.onlyfriends.api.model.repository.SubscriptionRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class SubscriptionsPostsServiceImpl(
    val postRepository: PostRepository,
    val subscriptionRepository: SubscriptionRepository
) : SubscriptionsPostsService {
    override fun getPosts(since: String, pageSize: Int): List<Post> {
        return postRepository.findAllByAuthorInAndCreatedAtLessThanOrderByCreatedAt(
            getBloggers().toMutableSet(),
            LocalDateTime.parse(since, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            PageRequest.of(0, pageSize)
        )
    }

    fun getBloggers(): Set<User> {
        val subs = subscriptionRepository.findAllBySubscriber(getPrincipal())
        return subs.map { it.blogger }.toSet()
    }



    private fun getPrincipal(): User = SecurityContextHolder.getContext().authentication.principal as User

}